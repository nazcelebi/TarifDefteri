package com.example.tarifdefteri.sebzeYemekleri;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tarifdefteri.R;
import com.example.tarifdefteri.sebzeYemekleri.mucver;

public class sebzeYemekleri extends AppCompatActivity {

    ListView listSebze;
    String msebzeBaslik[] = {"Kabak Mücver", "Barbunya Pilaki", "Ispanak Yemeği", "Pırasa Yemeği", "Patlıcan Musakka", "Etsiz Bamya Yemeği"};
    int msebzeResim[] = {R.drawable.mucver, R.drawable.pilaki, R.drawable.ispanak, R.drawable.pirasa, R.drawable.musakka, R.drawable.bamya};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sebze_yemekleri);

        listSebze = findViewById(R.id.listSebze);

        sebzeYemegiAdapter adapter = new sebzeYemegiAdapter(this, msebzeBaslik, msebzeResim);
        listSebze.setAdapter(adapter);

        listSebze.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    startActivity(new Intent(sebzeYemekleri.this, mucver.class));
                }
            }
        });
    }
    class sebzeYemegiAdapter extends ArrayAdapter<String> {
        Context context;
        String rbaslikSebze[];
        int rsebzeresim[];

        sebzeYemegiAdapter(Context b, String baslikSebze[], int sebzeyemegiresim[]) {
            super(b, R.layout.custom_sebze_yemekleri, R.id.txt_sebzeler, baslikSebze);
            this.context = b;
            this.rbaslikSebze = baslikSebze;
            this.rsebzeresim = sebzeyemegiresim;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View sebzeyemegisatir = layoutInflater.inflate(R.layout.custom_sebze_yemekleri, parent, false);
            ImageView sebzeyemekresim = sebzeyemegisatir.findViewById(R.id.sebzeresim);
            TextView sebzeyemeginbaslik = sebzeyemegisatir.findViewById(R.id.txt_sebzeler);

            sebzeyemekresim.setImageResource((rsebzeresim[position]));
            sebzeyemeginbaslik.setText((rbaslikSebze[position]));

            return sebzeyemegisatir;
        }
    }
}

