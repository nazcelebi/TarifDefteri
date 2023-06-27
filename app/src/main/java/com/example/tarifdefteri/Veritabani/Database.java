package com.example.tarifdefteri.Veritabani;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import com.example.tarifdefteri.model.Not;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "Notlar";
    final static int DATABASE_VERSION = 1;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOT = "CREATE TABLE Notlar (" + "id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "baslik VARCHAR ," +
                "notMetin VARCHAR ," +
                "imageUrl VARCHAR ," +
                "webUrl VARCHAR ," +
                "tarih VARCHAR)";
        db.execSQL(CREATE_NOT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Notlar ");
    }

    public void yeniNot(Not not) {
        SQLiteDatabase db = this.getReadableDatabase();
        //BURASI WRITABLE MI OLACAK?
        String INSERT_INTO = "INSERT INTO Notlar(baslik,notMetin,imageUrl,webUrl,tarih)" + "VALUES(" +
                "'" + not.getBaslik() + "' , " +
                "'" + not.getNotMetin() + "' ," +
                "'" + not.getImageUrl() + "' ," +
                "'" + not.getWebUrl() + "' ," +
                "'" + not.getTarih() + "')";
        db.execSQL(INSERT_INTO);
    }

    public ArrayList<Not> getNotlarim() {
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_NOTLAR = "SELECT * FROM Notlar ORDER BY id DESC";

        Cursor cursor = db.rawQuery(SELECT_NOTLAR, null);
        ArrayList<Not> notlar = new ArrayList<>();

        if (cursor != null) {
            cursor.moveToFirst();
            try { //d0 while kullanıldığı için veritabanı boş ise hata almamak için kullandık.
                do {
                    Not not = new Not();
                    not.setId(cursor.getInt(0));
                    not.setBaslik(cursor.getString(1));
                    not.setNotMetin(cursor.getString(2));
                    not.setImageUrl(cursor.getString(3));
                    not.setWebUrl(cursor.getString(4));
                    not.setTarih(cursor.getString(5));
                    notlar.add(not);
                } while (cursor.moveToNext());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return notlar;
    }

    public void NotGuncelle(Not not) {
        SQLiteDatabase db = this.getWritableDatabase();
        String UPDATE_NOT = "UPDATE Notlar SET" +
                " baslik='" + not.getBaslik() + "'," +
                " metinBaslik='" + not.getNotMetin() + "'," +
                " imageUrl='" + not.getImageUrl() + "',"  + "" +
                " webUrl='" + not.getWebUrl() + "',"  + "" +
                " tarih='" + not.getTarih() + "' WHERE id=" + not.getId();
        db.execSQL(UPDATE_NOT);
        db.close();
    }

    public void NotSil(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String DELETE_NOT = "DELETE FROM Notlar WHERE id =" + id;
        db.execSQL(DELETE_NOT);
        db.close();
    }
}
