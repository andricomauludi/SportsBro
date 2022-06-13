package com.example.cobaskripsi.ui.lapangan.basket.detail;

public class PemesananModel {
    String idtempat, idlapangan, namapemesan, nomortelppemesan, tanggalpemesan, timestamp, waktupemesanan;

    public PemesananModel(){

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

    public String getTanggalpemesan() {
        return tanggalpemesan;
    }

    public void setTanggalpemesan(String tanggalpemesan) {
        this.tanggalpemesan = tanggalpemesan;
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

    public PemesananModel(String idtempat, String idlapangan, String namapemesan, String nomortelppemesan, String tanggalpemesan, String timestamp, String waktupemesanan) {
        this.idtempat = idtempat;
        this.idlapangan = idlapangan;
        this.namapemesan = namapemesan;
        this.nomortelppemesan = nomortelppemesan;
        this.tanggalpemesan = tanggalpemesan;
        this.timestamp = timestamp;
        this.waktupemesanan = waktupemesanan;
    }
}
