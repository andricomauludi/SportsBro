package com.example.cobaskripsi.ui.lapangan.basket;

public class BasketModel {

    String olahraga;
    String namalapangan;
    String marker;
    String lokasi;
    String jarak;
    String latitudetempat;
    String longitudetempat;

    public BasketModel(String gambar) {
        this.gambar = gambar;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    String gambar;



    BasketModel(){

    }
    public BasketModel(String olahraga, String namalapangan, String marker, String lokasi, String jarak, String latitudetempat, String longitudetempat) {
        this.olahraga = olahraga;
        this.namalapangan = namalapangan;
        this.marker = marker;
        this.lokasi = lokasi;
        this.jarak = jarak;
        this.latitudetempat = latitudetempat;
        this.longitudetempat = longitudetempat;
    }

    public String getOlahraga() {
        return olahraga;
    }

    public void setOlahraga(String olahraga) {
        this.olahraga = olahraga;
    }

    public String getNamalapangan() {
        return namalapangan;
    }

    public void setNamalapangan(String namalapangan) {
        this.namalapangan = namalapangan;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getJarak() {
        return jarak;
    }

    public void setJarak(String jarak) {
        this.jarak = jarak;
    }

    public String getLatitudetempat() {
        return latitudetempat;
    }

    public void setLatitudetempat(String latitudetempat) {
        this.latitudetempat = latitudetempat;
    }

    public String getLongitudetempat() {
        return longitudetempat;
    }

    public void setLongitudetempat(String longitudetempat) {
        this.longitudetempat = longitudetempat;
    }

}
