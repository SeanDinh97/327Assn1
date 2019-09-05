package com.example.a327lab1;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Playlist> listOfPlaylists;

    public User(String username, String password, ArrayList<Playlist> listOfPlaylists) {
        this.username = username;
        this.password = password;
        this.listOfPlaylists = listOfPlaylists;
    }
}
