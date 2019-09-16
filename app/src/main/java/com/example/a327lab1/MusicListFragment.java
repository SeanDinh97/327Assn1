package com.example.a327lab1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a327lab1.models.Music;

import java.util.List;

public class MusicListFragment extends Fragment {

    private ArrayAdapter<String> listAdapter ;

    private List<Music> musicList;
    private ListView listView;
    public TextView title, year, artist;
    MusicJSONProcessor musicJSONProcessor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_list, container, false);

        listView = (ListView)view.findViewById(R.id.musicList);

        musicJSONProcessor = new MusicJSONProcessor(getContext());

        musicList = musicJSONProcessor.getListOfMusic();

        ArrayAdapter<Music> arrayAdapter = new ArrayAdapter<Music>(getContext(),android.R.layout.simple_list_item_1, musicList);

        listView.setAdapter(arrayAdapter);

        /**
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
         {

         });
         **/
        return view;
    }

}
