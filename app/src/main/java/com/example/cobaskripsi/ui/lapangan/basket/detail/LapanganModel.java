package com.example.cobaskripsi.ui.lapangan.basket.detail;

public class LapanganModel {
    String idtempat,jamtersedia,namalapangan;

    public LapanganModel(){

    }

    public LapanganModel(String idtempat, String jamtersedia, String namalapangan) {
        this.idtempat = idtempat;
        this.jamtersedia = jamtersedia;
        this.namalapangan = namalapangan;
    }

    public String getIdtempat() {
        return idtempat;
    }

    public void setIdtempat(String idtempat) {
        this.idtempat = idtempat;
    }

    public String getJamtersedia() {
        return jamtersedia;
    }

    public void setJamtersedia(String jamtersedia) {
        this.jamtersedia = jamtersedia;
    }

    public String getNamalapangan() {
        return namalapangan;
    }

    public void setNamalapangan(String namalapangan) {
        this.namalapangan = namalapangan;
    }
}
