package com.example.a327lab1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlaylistPopup extends AppCompatActivity {
    private UserJSONProcessor userJSONProcessor;

    private EditText newPlaylistName;
    private Button okBtn;
    private Button cancelbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_playlist_popup);

        initUIViews();

        userJSONProcessor = new UserJSONProcessor(this);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = (int)(dm.widthPixels * .8);
        int height = (int)(dm.heightPixels * .3);

        getWindow().setLayout(width,height);

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playlistName = newPlaylistName.getText().toString();
                if (!playlistName.isEmpty()) {
                    String userName = getIntent().getExtras().getString("name");
                    userJSONProcessor.addPlaylistToUser(userName, playlistName);
                    setResult(Activity.RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(AddPlaylistPopup.this, "Please enter playlist name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initUIViews() {
        newPlaylistName = findViewById(R.id.et_playlist_name_input);
        okBtn = findViewById(R.id.btnAddPlaylist_OK);
        cancelbtn = findViewById(R.id.btnAddPlaylist_CANCEL);
    }
}
