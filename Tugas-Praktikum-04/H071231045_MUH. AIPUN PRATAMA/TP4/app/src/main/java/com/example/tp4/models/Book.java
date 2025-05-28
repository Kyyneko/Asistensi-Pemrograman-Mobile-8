package com.example.tp4.models;

import android.net.Uri;

import java.util.concurrent.atomic.AtomicInteger;

public class Book {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int id;
    private String judul;
    private String penulis;
    private String tahunTerbit;
    private String genre;
    private String blurb;
    private int number;
    private String language;
    private String category;
    private String ISBN;
    private String country;
    private String publisher;
    private String edition;
    private int coverResId;
    private Uri coverUri;
    private int rating;
    private boolean isLike;

    public Book(String judul, String penulis, String tahunTerbit, String genre, String blurb, int number, String language, String category, String ISBN, String country, String publisher, String edition, int coverResId, int rating, boolean isLike) {
        this.id = count.incrementAndGet();
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.genre = genre;
        this.blurb = blurb;
        this.number = number;
        this.language = language;
        this.category = category;
        this.ISBN = ISBN;
        this.country = country;
        this.publisher = publisher;
        this.edition = edition;
        this.coverResId = coverResId;
        this.rating = rating;
        this.isLike = isLike;
    }

    public Book(String judul, String penulis, String tahunTerbit, String genre, String blurb, int number, String language, String category, String ISBN, String country, String publisher, String edition, Uri coverUri, int rating, boolean isLike) {
        this.id = count.incrementAndGet();
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.genre = genre;
        this.blurb = blurb;
        this.number = number;
        this.language = language;
        this.category = category;
        this.ISBN = ISBN;
        this.country = country;
        this.publisher = publisher;
        this.edition = edition;
        this.coverUri = coverUri;
        this.rating = rating;
        this.isLike = isLike;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public String getTahunTerbit() {
        return tahunTerbit;
    }

    public String getGenre() {
        return genre;
    }

    public String getBlurb() {
        return blurb;
    }

    public int getNumber() {
        return number;
    }

    public String getLanguage() {
        return language;
    }

    public String getCategory() {
        return category;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getCountry() {
        return country;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getEdition() {
        return edition;
    }

    public int getCoverResId() {
        return coverResId;
    }

    public Uri getCoverUri() {
        return coverUri;
    }

    public int getRating() {
        return rating;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean isLike) {
        this.isLike = isLike;
    }
}
