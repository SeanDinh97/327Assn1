package com.example.a327lab1;

import java.util.ArrayList;

public class Playlist {
    private String playlistName;
    private ArrayList<Song> songs;

    public Playlist(String playlistName, ArrayList<Song> songs) {
        this.playlistName = playlistName;
        this.songs = songs;
    }
}
