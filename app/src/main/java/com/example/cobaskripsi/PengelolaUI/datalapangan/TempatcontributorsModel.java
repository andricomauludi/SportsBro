package com.example.cobaskripsi.PengelolaUI.datalapangan;

public class TempatcontributorsModel {

    String idtempat, iduser, idtempatcontributors, namatempat, username;

    public String getNamatempat() {
        return namatempat;
    }

    public void setNamatempat(String namatempat) {
        this.namatempat = namatempat;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public TempatcontributorsModel(String idtempat, String iduser, String idtempatcontributors, String namatempat, String username) {
        this.idtempat = idtempat;
        this.iduser = iduser;
        this.idtempatcontributors = idtempatcontributors;
        this.namatempat = namatempat;
        this.username = username;
    }

    public String getIdtempatcontributors() {
        return idtempatcontributors;
    }

    public void setIdtempatcontributors(String idtempatcontributors) {
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
