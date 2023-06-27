package com.example.tarifdefteri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AnaSayfaDenemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa_deneme);
    }

    public void click1(View view) {
        startActivity(new Intent(this, DenemeActivity.class).putExtra("parametre","tarif1"));
    }
    public void click2(View view) {
        startActivity(new Intent(this, DenemeActivity.class).putExtra("parametre","tarif2"));
    }
    public void click3(View view) {
        startActivity(new Intent(this, DenemeActivity.class).putExtra("parametre","tarif3"));
    }
}