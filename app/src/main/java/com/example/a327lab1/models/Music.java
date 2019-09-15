//Work in Progress
package com.example.a327lab1.models;

import android.support.annotation.NonNull;

public class Music {
    private Release release;
    private Artist artist;
    private Song song;

    public Music(Release release, Artist artist, Song song) {
        this.release = release;
        this.artist = artist;
        this.song = song;
    }

    public String getSongTitle()
    {
        return song.getSongTitle();
    }

    public String getReleaseDate()
    {
        return release.getReleaseDate();
    }

    public String getArtistName()
    {
        return artist.getArtistName();
    }
}