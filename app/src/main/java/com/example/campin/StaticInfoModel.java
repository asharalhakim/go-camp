package com.example.campin;

public class StaticInfoModel {

    private int gambar;
    private String judul, deskripsi;

    public StaticInfoModel(int gambar, String judul, String deskripsi) {
        this.gambar = gambar;
        this.judul = judul;
        this.deskripsi = deskripsi;
    }

    public int getGambar() {
        return gambar;
    }

    public String getJudul() {
        return judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}
