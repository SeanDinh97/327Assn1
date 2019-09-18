package com.example.a327lab1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a327lab1.models.Playlist;

import java.util.ArrayList;

/**
 * Activity's user interface of the Play List.
 */
public class PlaylistFragment extends Fragment {

    private static final String TAG = "PlaylistFragment";

    private UserJSONProcessor userJSONProcessor;

    private String userName;
    private ArrayList<Playlist> listOfPlaylists;
    private ArrayList<String> playlistNames;
    private String newPlaylistName;

    private FloatingActionButton addPlaylistBtn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_playlist, container, false);

        initAttributes();
        initUIViews(view);
        initRecyclerView(view);

        addPlaylistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),AddPlaylistPopup.class);
                i.putExtra("name", userName);
                startActivityForResult(i, 10001);
            }
        });
        return view;
    }

    private void initAttributes() {
        userJSONProcessor = new UserJSONProcessor(getContext());
        userName = getActivity().getIntent().getExtras().getString("name");
        listOfPlaylists = userJSONProcessor.getListOfPlaylistsFromUser(userName);
        playlistNames = getPlaylistNames(listOfPlaylists);
    }

    private void initUIViews(View view) {
        addPlaylistBtn = view.findViewById(R.id.btnAddPlaylist);
    }

    private void initRecyclerView(View view) {
        Log.d(TAG, "initRecyclerView: init recyclerview.");

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_playlist);
        PlaylistAdapter adapter = new PlaylistAdapter( userName, playlistNames, view.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager( view.getContext()));
    }

    private ArrayList<String> getPlaylistNames(ArrayList<Playlist> list) {
        ArrayList<String> nameList = new ArrayList<String>();
        for (Playlist playlist : list) {
            nameList.add(playlist.getPlaylistName());
        }
        return nameList;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 10001) && (resultCode == Activity.RESULT_OK)) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
        }
    }
}
