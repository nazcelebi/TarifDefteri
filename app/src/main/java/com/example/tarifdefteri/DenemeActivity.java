package com.example.tarifdefteri;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DenemeActivity extends AppCompatActivity {

    String parametre="";
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deneme);

        parametre = getIntent().getStringExtra("parametre");

        text = findViewById(R.id.txt_deneme);
        textDoldur();

    }

    private void textDoldur() {
        switch (parametre) {
            case "tarif1":
                text.setText("Domates Çorbası");
                break;
            case "tarif2":
                text.setText("Domates2 Çorbası");
                break;
            case "tarif3":
                text.setText("Domates3 Çorbası");
                break;
        }
    }
}