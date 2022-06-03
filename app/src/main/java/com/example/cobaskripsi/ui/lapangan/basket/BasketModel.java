package com.example.cobaskripsi.ui.lapangan.basket;

public class BasketModel {
    String gambar,marker,namatempat;

    public BasketModel(String gambar, String marker, String namatempat) {
        this.gambar = gambar;
        this.marker = marker;
        this.namatempat = namatempat;
    }

    BasketModel(){

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
