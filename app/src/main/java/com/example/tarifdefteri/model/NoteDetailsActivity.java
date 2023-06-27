package com.example.tarifdefteri.model;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tarifdefteri.R;
import com.example.tarifdefteri.Veritabani.Database;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteDetailsActivity extends AppCompatActivity {

    private static final int PERMISSION_CODE = 99;
    private static final int SELECT_IMAGE = 100;


    String type = null;
    EditText inputTitle, inputNote;
    TextView textDate;
    Database db;

    ConstraintLayout imageContainer;
    ImageView imageNoteImage;

    String imageUrl = null;

    LinearLayout layoutUrl;
    TextView textUrl;

    Not selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        type = getIntent().getStringExtra("type");
        db = new Database(getApplicationContext());
        /*-----------------------------------------*/

        inputTitle = findViewById(R.id.inputTitle);
        inputNote = findViewById(R.id.inputNote);
        textDate = findViewById(R.id.textDate);
        imageContainer = findViewById(R.id.imageNoteContainer);
        imageNoteImage = findViewById(R.id.imageNoteImage);
        layoutUrl = findViewById(R.id.layoutUrl);
        textUrl = findViewById(R.id.textNoteUrl);

        /*-----------------------------------------*/


        if (type.equals("updateNote")) {
            selectedNote = (Not) getIntent().getSerializableExtra("selectedNote");
            inputTitle.setText(selectedNote.getBaslik());
            inputNote.setText(selectedNote.getNotMetin());
            textDate.setText(selectedNote.getTarih());

            if (selectedNote.getImageUrl() != null && selectedNote.getImageUrl().length() > 4) {
                imageContainer.setVisibility(View.VISIBLE);
                imageNoteImage.setImageBitmap(BitmapFactory.decodeFile(selectedNote.getWebUrl()));
            }
            if (selectedNote.getWebUrl() != null && selectedNote.getWebUrl().length() > 4) {
                layoutUrl.setVisibility(View.VISIBLE);
                textUrl.setText(selectedNote.getWebUrl());
            }
        }
        else
        {
            textDate.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm", Locale.getDefault()).format(new Date()));
        }

        findViewById(R.id.imageSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputTitle.getText().toString().trim().isEmpty()) {
                    inputTitle.setError("Başlık Giriniz!");
                } else if (inputNote.getText().toString().trim().isEmpty()) {
                    inputNote.setError("Lütfen Not Giriniz!");
                } else {
                    if (type.equals("updateNote")) {
                        db.NotGuncelle(new Not(selectedNote.getId(),
                                inputTitle.getText().toString(),
                                inputNote.getText().toString(),
                                imageUrl,
                                layoutUrl.getVisibility() == View.VISIBLE ? textUrl.getText().toString() : null,
                                textDate.getText().toString()));
                    } else {
                        db.yeniNot(new Not(inputTitle.getText().toString(),
                                inputNote.getText().toString(),
                                imageUrl, layoutUrl.getVisibility() == View.VISIBLE ? textUrl.getText().toString() : null, textDate.getText().toString()));
                    }
                    finish();
                }
            }
        });
        /*--------------------------------------------------------------------*/
        findViewById(R.id.imageBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.imageAddWebUrl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWebUrl();
            }
        });
        findViewById(R.id.imageDeletUrl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textUrl.setText("");
                layoutUrl.setVisibility(View.GONE);
            }
        });
        /*---------------------------------------------------------------------*/
        findViewById(R.id.imageAddImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });
    }
    private void addWebUrl() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_url);

        TextView textCancel = dialog.findViewById(R.id.textCancel);
        TextView textAddUrl = dialog.findViewById(R.id.textUrlAdd);
        EditText inputUrl = dialog.findViewById(R.id.inputURL);

        textCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        textAddUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputUrl.getText().toString().isEmpty() &&
                        Patterns.WEB_URL.matcher(inputUrl.getText().toString()).matches()) {
                    //işlemler
                    textUrl.setText(inputUrl.getText().toString());
                    layoutUrl.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                } else {
                    inputUrl.setError("Lütfen geçerli bir Url giriniz!");
                }
            }
        });

        dialog.show();
    }

    /*-----------------------------------------*/
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions, PERMISSION_CODE);
            } else {
                pickImage();
            }
        } else {
            pickImage();
        }
    }

    /*-----------------------------------------*/

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_IMAGE);
    }

    /*-----------------------------------------*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        }
    }

    /*-----------------------------------------*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imageNoteImage.setImageBitmap(bitmap);
                    imageContainer.setVisibility(View.VISIBLE);
                    imageUrl = getPath(uri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*-----------------------------------------*/

    private String getPath(Uri uri) {
        String filePath;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            filePath = uri.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filePath = cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }
}