package com.example.a327lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.a327lab1.models.User;

public class PlaylistActivity extends AppCompatActivity {

    private TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        userName = (TextView)findViewById(R.id.tvUserName);

        String name = getIntent().getExtras().getString("name");
        userName.setText(name);
    }

    public void logOut(View view) {
        new User(PlaylistActivity.this).removeUser();
        Intent i = new Intent(PlaylistActivity.this, LoginActivity.class);
        startActivity(i);
    }
}
