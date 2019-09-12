package com.example.a327lab1;

import android.content.Context;
import android.content.res.Resources;

import com.example.a327lab1.models.Music;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class MusicJSONProcessor {
    private Context context;
    private ArrayList<Music> listOfMusic;

    public MusicJSONProcessor(Context context) {
        this.context = context;
        this.listOfMusic = getMusicFromJSON();
    }

    private ArrayList<Music> getMusicFromJSON() {
        Resources res = context.getResources();
        InputStream is = res.openRawResource(R.raw.music);
        Scanner scanner = new Scanner(is);

        StringBuilder musicSB = new StringBuilder();
        while (scanner.hasNextLine()) {
            musicSB.append(scanner.nextLine());
        }

        Gson gson = new Gson();

        Type musicType = new TypeToken<ArrayList<Music>>() {}.getType();
        listOfMusic = gson.fromJson(musicSB.toString(), musicType);

        return listOfMusic;
    }
}
