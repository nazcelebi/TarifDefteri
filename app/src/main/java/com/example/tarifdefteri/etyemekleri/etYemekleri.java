package com.example.tarifdefteri.etyemekleri;

import android.annotation.SuppressLint;
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

import com.example.tarifdefteri.etyemekleri.EtliNohut;
import com.example.tarifdefteri.etyemekleri.Etsote;
import com.example.tarifdefteri.etyemekleri.Karniyarik;
import com.example.tarifdefteri.R;
import com.example.tarifdefteri.etyemekleri.alinazik;
import com.example.tarifdefteri.etyemekleri.soslubiftek;
import com.example.tarifdefteri.etyemekleri.taskebabi;

public class etYemekleri extends AppCompatActivity {

    ListView listet;
    String mEtyemekbaslik[] = {"Ali Nazik" ,"Etli Nohut" , "Et Sote" ,"Karnıyarık","Soslu Biftek","Tas Kebabı"};
    int mEtyemekresim [] = {R.drawable.alinazik,R.drawable.etlinohut,R.drawable.etsote,R.drawable.karniyarik,R.drawable.soslubiftek,R.drawable.taskebabi};


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_et_yemekleri);

        listet = findViewById(R.id.listet);
        etyemegiadapter adapter= new etyemegiadapter(this,mEtyemekbaslik,mEtyemekresim);
        listet.setAdapter(adapter);

        listet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {startActivity(new Intent(etYemekleri.this, alinazik.class));}
                else if(position==1)
                {startActivity(new Intent(etYemekleri.this, EtliNohut.class));}
                else if(position==2)
                {startActivity(new Intent(etYemekleri.this, Etsote.class));}
                else if(position==3)
                {startActivity(new Intent(etYemekleri.this, Karniyarik.class));}
                else if(position==4)
                {startActivity(new Intent(etYemekleri.this, soslubiftek.class));}
                else if(position==5)
                {startActivity(new Intent(etYemekleri.this, taskebabi.class));}
                {}
            }
        });


    }
    class etyemegiadapter extends ArrayAdapter <String>
    {
        Context context;
        String rbasliketyemegi[];
        int retyemegiresim[];

        etyemegiadapter (Context b ,String basliketyemegi [], int etyemegiresim [])
        {
            super(b,R.layout.custom_etyemekleri,R.id.txt_etyemegi,basliketyemegi);
            this.context=b;
            this.rbasliketyemegi=basliketyemegi;
            this.retyemegiresim=etyemegiresim;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           View etyemeksatir = layoutInflater.inflate(R.layout.custom_etyemekleri,parent,false);
           ImageView etyemekresim = etyemeksatir.findViewById(R.id.etyemegi);
           TextView etyemeginbaslik = etyemeksatir.findViewById(R.id.txt_etyemegi);

            etyemekresim.setImageResource(retyemegiresim[position]);
            etyemeginbaslik.setText(rbasliketyemegi[position]);

            return etyemeksatir;

        }
    }
}