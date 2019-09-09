package com.example.a327lab1;

import android.content.Context;
import android.content.res.Resources;

import com.example.a327lab1.models.Song;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class SongJSONProcessor {
    private Context context;
    private ArrayList<Song> listOfSongs;

    public SongJSONProcessor(Context context) {
        this.context = context;
        this.listOfSongs = getSongsFromJSON();
    }

    private ArrayList<Song> getSongsFromJSON() {
        Resources res = context.getResources();
        InputStream is = res.openRawResource(R.raw.music);
        Scanner scanner = new Scanner(is);

        StringBuilder songSB = new StringBuilder();
        while (scanner.hasNextLine()) {
            songSB.append(scanner.nextLine());
        }

        Gson gson = new Gson();

        Type songType = new TypeToken<ArrayList<Song>>() {}.getType();
        listOfSongs = gson.fromJson(songSB.toString(), songType);

        return listOfSongs;
    }
}
