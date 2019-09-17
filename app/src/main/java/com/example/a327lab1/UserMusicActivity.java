package com.example.a327lab1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.a327lab1.models.Music;

import java.util.ArrayList;

public class UserMusicActivity extends AppCompatActivity {

    private static final String TAG = "UserMusicActivity";

    private UserJSONProcessor userJSONProcessor;

    private String userName;
    private String playlistName;
    private ArrayList<Music> userMusicList;

    public TextView title, year, artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_music);

        userJSONProcessor = new UserJSONProcessor(this);

        initAttributes();
        initRecyclerView();
    }

    private void initAttributes() {
        userName = getIntent().getExtras().getString("userName");
        playlistName = getIntent().getExtras().getString("playlistName");
        userMusicList = userJSONProcessor.getListOfMusicFromPlaylist(userName, playlistName);
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: initRecyclerView");

        RecyclerView recyclerView = findViewById(R.id.recycler_view_user_music);
        UserMusicAdapter adapter = new UserMusicAdapter(this, userMusicList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
    }
}
