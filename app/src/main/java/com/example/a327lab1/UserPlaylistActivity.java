package com.example.a327lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a327lab1.models.Music;

import java.util.ArrayList;

public class UserPlaylistActivity extends AppCompatActivity {

    private UserJSONProcessor userJSONProcessor;

    private String userName;
    private String playlistName;
    private ArrayList<Music> userListOfMusic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_playlist);

        initAttributes();
        //initUIViews(view);
        //initRecyclerView(view);
    }

    private void initAttributes() {
        userName = getIntent().getExtras().getString("name");
        playlistName = getIntent().getExtras().getString("playlist");
        userListOfMusic  = userJSONProcessor.getListOfMusicFromPlaylist(userName, playlistName);
    }
}
