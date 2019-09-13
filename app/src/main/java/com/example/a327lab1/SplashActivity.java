package com.example.a327lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.a327lab1.models.User;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        final User user = new User (SplashActivity.this);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (user.getName() != "") {
                    Intent i = new Intent(SplashActivity.this, PlaylistActivity.class);
                    i.putExtra("name", user.getName());
                    i.putExtra("listOfPlaylists", user.getListOfPlaylists());
                    startActivity(i);
                    finish();
                } else {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        },2000);
    }

}
