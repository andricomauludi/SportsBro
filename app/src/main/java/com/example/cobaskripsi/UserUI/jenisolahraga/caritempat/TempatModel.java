package com.example.cobaskripsi.UserUI.jenisolahraga.caritempat;

public class TempatModel {
    String gambar,marker,namatempat, jenisolahraga;

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

    public String getJenisolahraga() {
        return jenisolahraga;
    }

    public void setJenisolahraga(String jenisolahraga) {
        this.jenisolahraga = jenisolahraga;
    }

    public TempatModel(String gambar, String marker, String namatempat, String jenisolahraga) {
        this.gambar = gambar;
        this.marker = marker;
        this.namatempat = namatempat;
        this.jenisolahraga = jenisolahraga;
    }

}
