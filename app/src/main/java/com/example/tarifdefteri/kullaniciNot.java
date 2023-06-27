package com.example.tarifdefteri;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarifdefteri.Veritabani.Database;
import com.example.tarifdefteri.adapter.NoteAdapter;
import com.example.tarifdefteri.model.Not;
import com.example.tarifdefteri.model.NoteDetailsActivity;

import java.util.ArrayList;

public class kullaniciNot extends AppCompatActivity implements NoteListener{

    ImageView imageAddNote;
    Database db;
    NoteAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Not> notListesi;

       /*-----------------------------------------*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_not);
        initialize();
    }

       /*-----------------------------------------*/

    void initialize() {
        db = new Database(this);
        notListesi = new ArrayList<>();

        imageAddNote = findViewById(R.id.imageAddNote);
        recyclerView = findViewById(R.id.recyclerViewNotlar);

        notListesi.addAll(db.getNotlarim());

        /*-----------------------------------------*/

        //not ekle kısmına click işlemi için
        NoteListener listener = new NoteListener() {
            @Override
            public void NoteClick(Not not) {
            }
        };

        /*-----------------------------------------*/

        adapter = new NoteAdapter(notListesi, listener);
        recyclerView.setAdapter(adapter);

        /*-----------------------------------------*/

        imageAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(kullaniciNot.this, NoteDetailsActivity.class);
                intent.putExtra("type","newNote");
                startActivity(intent);
            }
        });
    }

        /*-----------------------------------------*/

    void updateNotLİstesi(){
        notListesi.clear();
        notListesi.addAll(db.getNotlarim());
        //not listesini güncelledim ve veritabanından notları yeniden aldım
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateNotLİstesi();
    }

    @Override
    public void NoteClick(Not not) {
        Intent intent = new Intent(kullaniciNot.this, NoteDetailsActivity.class);
        intent.putExtra("type","updateNote");
        intent.putExtra("selectedNote" , not);
        startActivity(intent);
    }
}