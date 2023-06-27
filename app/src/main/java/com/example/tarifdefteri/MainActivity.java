package com.example.tarifdefteri;

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

import com.example.tarifdefteri.corbalar.corbalar;
import com.example.tarifdefteri.etyemekleri.etYemekleri;
import com.example.tarifdefteri.sebzeYemekleri.sebzeYemekleri;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    String mBaslik[]= {"Çorbalar" , "Et Yemekleri" , "Sebze Yemekleri" , "Makarnalar" , "Hamur İşleri", "Tatlılar","Zeytinyağlılar" ,"Salatalar" };
    int mresim[]={R.drawable.soup,R.drawable.meat,R.drawable.vegetable,R.drawable.spaghetti,R.drawable.pastry,R.drawable.sweet,R.drawable.sarma,R.drawable.salad};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          listView=findViewById(R.id.ListView);
          benimAdapter adapter= new benimAdapter(this,mBaslik,mresim);
          listView.setAdapter(adapter);

          listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  if(position==0)
                  {
                      startActivity(new Intent(MainActivity.this, corbalar.class));
                  }
                  if(position==1){
                      startActivity(new Intent(MainActivity.this, etYemekleri.class));
                  }
                  if(position==2){
                      startActivity(new Intent(MainActivity.this, sebzeYemekleri.class));
                  }
                  if(position==3){
                      startActivity(new Intent(MainActivity.this,Makarnalar.class));
                  }
              }
          });
    }
    class benimAdapter extends ArrayAdapter<String>{
         Context context;
         String rBaslik[];
         int rResim[];

         benimAdapter(Context c ,String baslik[] , int resim[])
         {
             super(c,R.layout.custom_listview,R.id.text1,baslik);
             this.context=c;
             this.rBaslik =baslik;
             this.rResim =resim;
         }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater =(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View satir = layoutInflater.inflate(R.layout.custom_listview,parent,false);
            ImageView resim=satir.findViewById(R.id.resim);
            TextView benimBaslik =satir.findViewById(R.id.text1);

            resim.setImageResource(rResim[position]);
            benimBaslik.setText(rBaslik[position]);


            return satir;
        }
    }
}