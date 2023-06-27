package com.example.tarifdefteri.model;

import java.io.Serializable;

public class Not implements Serializable {
    int id;
    String baslik;
    String notMetin;
    String imageUrl;
    String webUrl;
    String tarih;

    public Not(String baslik, String notMetin, String imageUrl,String webUrl, String tarih) {
        this.baslik = baslik;
        this.imageUrl = imageUrl;
        this.notMetin = notMetin;
        this.webUrl=webUrl;
        this.tarih = tarih;
    }

    public Not(int id, String baslik, String notMetin, String imageUrl,String webUrl, String tarih) {
        this.id = id;
        this.baslik = baslik;
        this.notMetin = notMetin;
        this.imageUrl = imageUrl;
        this.webUrl=webUrl;
        this.tarih = tarih;
    }

    public Not() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getNotMetin() {
        return notMetin;
    }

    public void setNotMetin(String notMetin) {
        this.notMetin = notMetin;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }


}
