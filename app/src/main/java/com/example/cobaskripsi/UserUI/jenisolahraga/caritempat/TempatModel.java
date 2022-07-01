package com.example.cobaskripsi.UserUI.jenisolahraga.caritempat;

public class TempatModel {
    String gambar,marker,namatempat, jenisolahraga,alamattempat,notelptempat;

    public TempatModel(String gambar, String marker, String namatempat, String jenisolahraga, String alamattempat, String notelptempat) {
        this.gambar = gambar;
        this.marker = marker;
        this.namatempat = namatempat;
        this.jenisolahraga = jenisolahraga;
        this.alamattempat = alamattempat;
        this.notelptempat = notelptempat;
    }

    public String getNotelptempat() {
        return notelptempat;
    }

    public void setNotelptempat(String notelptempat) {
        this.notelptempat = notelptempat;
    }

    public String getAlamattempat() {
        return alamattempat;
    }

    public void setAlamattempat(String alamattempat) {
        this.alamattempat = alamattempat;
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

    public String getJenisolahraga() {
        return jenisolahraga;
    }

    public void setJenisolahraga(String jenisolahraga) {
        this.jenisolahraga = jenisolahraga;
    }


}
