package com.example.cobaskripsi.PengelolaUI.datalapangan;

public class TempatcontributorsModel {

    String idtempat, iduser;

    TempatcontributorsModel() {

    }

    public String getIdtempat() {
        return idtempat;
    }

    public void setIdtempat(String idtempat) {
        this.idtempat = idtempat;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public TempatcontributorsModel(String idtempat, String iduser) {
        this.idtempat = idtempat;
        this.iduser = iduser;
    }
}
