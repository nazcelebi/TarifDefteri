package com.example.tarifdefteri.corbalar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.tarifdefteri.R;
import com.example.tarifdefteri.corbalar.balkabagicorbasi;
import com.example.tarifdefteri.corbalar.domatescorbasi;
import com.example.tarifdefteri.corbalar.duguncorbasi;
import com.example.tarifdefteri.corbalar.mantarcorbasi;
import com.example.tarifdefteri.corbalar.mercimekcorbasi;
import com.example.tarifdefteri.corbalar.tarhanacorbasi;
import com.example.tarifdefteri.corbalar.topalakcorbasi;
import com.example.tarifdefteri.corbalar.yaylacorbasi;
import com.example.tarifdefteri.corbalar.yogurtcorbasi;

public class corbalar extends AppCompatActivity {

    ListView listcorba;
    String mbaslikCorba[] = {"Domates Çorbası","Mantar Çorbası" , "Mercimek Çorbası" , "Yayla Çorbası" , "Yoğurt Çorbası","Tarhana Çorbası","Düğün Çorbası","Balkabağı Çorbası","Topalak Çorbası"};
    int mcorbaresim [] = {R.drawable.domates,R.drawable.mantar,R.drawable.mercimek,R.drawable.yayla,R.drawable.yogurt,R.drawable.tarhana,R.drawable.dugun,R.drawable.kabak,R.drawable.topalak};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corbalar);

       listcorba = findViewById(R.id.listcorba);

        corbaAdapter adapter=new corbaAdapter(this,mbaslikCorba,mcorbaresim);
        listcorba.setAdapter(adapter);


        listcorba.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {startActivity(new Intent(corbalar.this, domatescorbasi.class));}
                else if(position==1)
                {startActivity(new Intent(corbalar.this, mantarcorbasi.class));}
                else if(position==2)
                {startActivity(new Intent(corbalar.this, mercimekcorbasi.class));}
                else if(position==3)
                {startActivity(new Intent(corbalar.this, yaylacorbasi.class));}
                else if(position==4)
                {startActivity(new Intent(corbalar.this, yogurtcorbasi.class));}
                else if(position==5)
                {startActivity(new Intent(corbalar.this, tarhanacorbasi.class));}
                else if(position==6)
                {startActivity(new Intent(corbalar.this, duguncorbasi.class));}
                else if(position==7)
                {startActivity(new Intent(corbalar.this, balkabagicorbasi.class));}
                else if(position==8)
                {startActivity(new Intent(corbalar.this, topalakcorbasi.class));}
            }
        });
    }

    class corbaAdapter extends ArrayAdapter <String>
    {
        Context context;
        String rbaslikCorba[];
        int rcorbaresim[];

        corbaAdapter (Context a,String baslikCorba [], int corbaresim [])
        {
            super(a,R.layout.custom_corbalar,R.id.txt_corbalar,baslikCorba);
            this.context=a;
            this.rbaslikCorba=baslikCorba;
            this.rcorbaresim=corbaresim;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View corbasatir = layoutInflater.inflate(R.layout.custom_corbalar, parent, false);
            ImageView corbaresim = corbasatir.findViewById(R.id.corbaresim);
            TextView corbaninBaslik = corbasatir.findViewById(R.id.txt_corbalar);

            corbaresim.setImageResource(rcorbaresim[position]);
            corbaninBaslik.setText(rbaslikCorba[position]);

            return corbasatir;
        }
    }
}