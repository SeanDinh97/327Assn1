package com.example.a327lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent intent = new Intent(this, PlaylistActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            case R.id.song_list:
                startActivity(new Intent(this, SongListActivity.class));
            case R.id.nav_signout:
                startActivity(new Intent(this, LoginActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

