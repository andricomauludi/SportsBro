package com.example.cobaskripsi.UserUI.jenisolahraga.caritempat.detail;

public class PemesananModel {
    String idtempat;
    String idlapangan;
    String namapemesan;
    String nomortelppemesan;
    String tanggalpemesanan;
    String timestamp;
    String waktupemesanan;
    String jenisolahraga;
    String namalapangan;
    String statuspemesanan;
    String idpemesanan;
    String iduser;

    public PemesananModel(String idtempat, String idlapangan, String namapemesan, String nomortelppemesan, String tanggalpemesanan, String timestamp, String waktupemesanan, String jenisolahraga, String namalapangan, String statuspemesanan, String idpemesanan, String iduser, String namatempat) {
        this.idtempat = idtempat;
        this.idlapangan = idlapangan;
        this.namapemesan = namapemesan;
        this.nomortelppemesan = nomortelppemesan;
        this.tanggalpemesanan = tanggalpemesanan;
        this.timestamp = timestamp;
        this.waktupemesanan = waktupemesanan;
        this.jenisolahraga = jenisolahraga;
        this.namalapangan = namalapangan;
        this.statuspemesanan = statuspemesanan;
        this.idpemesanan = idpemesanan;
        this.iduser = iduser;
        this.namatempat = namatempat;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }
    public String getIdpemesanan() {
        return idpemesanan;
    }

    public void setIdpemesanan(String idpemesanan) {
        this.idpemesanan = idpemesanan;
    }

    public String getStatuspemesanan() {
        return statuspemesanan;
    }

    public void setStatuspemesanan(String statuspemesanan) {
        this.statuspemesanan = statuspemesanan;
    }

    public String getIdtempat() {
        return idtempat;
    }

    public void setIdtempat(String idtempat) {
        this.idtempat = idtempat;
    }

    public String getIdlapangan() {
        return idlapangan;
    }

    public void setIdlapangan(String idlapangan) {
        this.idlapangan = idlapangan;
    }

    public String getNamapemesan() {
        return namapemesan;
    }

    public void setNamapemesan(String namapemesan) {
        this.namapemesan = namapemesan;
    }

    public String getNomortelppemesan() {
        return nomortelppemesan;
    }

    public void setNomortelppemesan(String nomortelppemesan) {
        this.nomortelppemesan = nomortelppemesan;
    }

    public String getTanggalpemesanan() {
        return tanggalpemesanan;
    }

    public void setTanggalpemesanan(String tanggalpemesanan) {
        this.tanggalpemesanan = tanggalpemesanan;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getWaktupemesanan() {
        return waktupemesanan;
    }

    public void setWaktupemesanan(String waktupemesanan) {
        this.waktupemesanan = waktupemesanan;
    }

    public String getJenisolahraga() {
        return jenisolahraga;
    }

    public void setJenisolahraga(String jenisolahraga) {
        this.jenisolahraga = jenisolahraga;
    }

    public String getNamalapangan() {
        return namalapangan;
    }

    public void setNamalapangan(String namalapangan) {
        this.namalapangan = namalapangan;
    }

    public String getNamatempat() {
        return namatempat;
    }

    public void setNamatempat(String namatempat) {
        this.namatempat = namatempat;
    }

    String namatempat;

    public PemesananModel(){

    }


}
