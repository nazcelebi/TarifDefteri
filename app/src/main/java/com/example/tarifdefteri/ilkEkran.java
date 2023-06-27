package com.example.tarifdefteri;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ilkEkran extends AppCompatActivity {

    Button btn_tarif;
    Button btn_not;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilk_ekran);

        btn_tarif = findViewById(R.id.btn_tarif);
        btn_not = findViewById(R.id.btn_not);

        btn_tarif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tarif = new Intent(ilkEkran.this, MainActivity.class);
                startActivity(tarif);
            }
        });

        btn_not .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent not = new Intent(ilkEkran.this, kullaniciNot.class);
                startActivity(not);
            }
        });
    }
}