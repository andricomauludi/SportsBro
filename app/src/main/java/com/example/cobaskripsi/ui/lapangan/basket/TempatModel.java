package com.example.cobaskripsi.ui.lapangan.basket;

public class TempatModel {
    String gambar,marker,namatempat;

    public TempatModel(String gambar, String marker, String namatempat) {
        this.gambar = gambar;
        this.marker = marker;
        this.namatempat = namatempat;
    }

    TempatModel(){

    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getNamatempat() {
        return namatempat;
    }

    public void setNamatempat(String namatempat) {
        this.namatempat = namatempat;
    }
}
