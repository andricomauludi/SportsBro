package com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail;

public class LapanganModel {
    String idtempat,jamtersedia,namalapangan,deskripsilapangan;

    public LapanganModel(String idtempat, String jamtersedia, String namalapangan, String deskripsilapangan) {
        this.idtempat = idtempat;
        this.jamtersedia = jamtersedia;
        this.namalapangan = namalapangan;
        this.deskripsilapangan = deskripsilapangan;
    }

    public String getDeskripsilapangan() {
        return deskripsilapangan;
    }

    public void setDeskripsilapangan(String deskripsilapangan) {
        this.deskripsilapangan = deskripsilapangan;
    }

    public LapanganModel(){

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
