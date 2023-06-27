package com.example.tarifdefteri;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    Handler h=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

       h.postDelayed(new Runnable() {
           @Override
           public void run() {

                   Intent intent =new Intent(SplashScreen.this, ilkEkran.class);
               startActivity(intent);
               }
           },2000);

        }
    }


