package com.example.a327lab1.models;

public class Artist {
    private double terms_freq;
    private String terms;
    private String name;
    private double familiarity;
    private double longitude;
    private String id;
    private String location;
    private double latitude;
    private String similar;
    private double hotttnesss;

    public Artist(double terms_freq, String terms, String name, double familiarity, double longitude, String id, String location, double latitude, String similar, double hotttnesss) {
        this.terms_freq = terms_freq;
        this.terms = terms;
        this.name = name;
        this.familiarity = familiarity;
        this.longitude = longitude;
        this.id = id;
        this.location = location;
        this.latitude = latitude;
        this.similar = similar;
        this.hotttnesss = hotttnesss;
    }

    public String getArtistName()
    {
        return name;
    }
}
