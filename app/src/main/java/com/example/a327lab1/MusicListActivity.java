package com.example.a327lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a327lab1.models.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicListActivity extends AppCompatActivity {

    private ArrayAdapter<String> listAdapter ;

    private List<Music> musicList;
    private ListView listView;
    public TextView title, year, artist;
    MusicJSONProcessor musicJSONProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        listView = (ListView) findViewById(R.id.musicList);

        musicJSONProcessor = new MusicJSONProcessor(this);

        musicList = musicJSONProcessor.getListOfMusic();

        ArrayAdapter<Music> arrayAdapter = new ArrayAdapter<Music>(this,android.R.layout.simple_list_item_1, musicList);

        listView.setAdapter(arrayAdapter);

        /**
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

        });
         **/
    }
}
