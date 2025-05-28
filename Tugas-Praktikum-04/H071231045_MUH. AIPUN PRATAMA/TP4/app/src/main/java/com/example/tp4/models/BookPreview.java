package com.example.tp4.models;

import android.net.Uri;

public class BookPreview {
    int id;
    private String judul;
    private String penulis;
    private int rating;
    private int coverResId;
    private Uri coverUri;
    private String genre;


    public BookPreview(int id, String judul, String penulis, int rating, int coverResId, String genre) {
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.rating = rating;
        this.coverResId = coverResId;
        this.genre = genre;
    }

    public BookPreview(int id, String judul, String penulis, int rating, Uri coverUri, String genre) {
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.rating = rating;
        this.coverUri = coverUri;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public int getCoverResId() {
        return coverResId;
    }

    public void setCoverResId(int coverResId) {
        this.coverResId = coverResId;
    }

    public Uri getCoverUri() {
        return coverUri;
    }

    public void setCoverUri(Uri coverUri) {
        this.coverUri = coverUri;
    }

    public String getGenre() {
        return genre;
    }
}
