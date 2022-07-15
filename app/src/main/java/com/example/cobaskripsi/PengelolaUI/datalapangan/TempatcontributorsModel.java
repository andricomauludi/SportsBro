package com.example.cobaskripsi.PengelolaUI.datalapangan;

public class TempatcontributorsModel {

    String idtempat, iduser, idtempatcontributors;

    public String getIdtempatcontributors() {
        return idtempatcontributors;
    }

    public void setIdtempatcontributors(String idtempatcontributors) {
        this.idtempatcontributors = idtempatcontributors;
    }

    public TempatcontributorsModel(String idtempat, String iduser, String idtempatcontributors) {
        this.idtempat = idtempat;
        this.iduser = iduser;
        this.idtempatcontributors = idtempatcontributors;
    }

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


}
