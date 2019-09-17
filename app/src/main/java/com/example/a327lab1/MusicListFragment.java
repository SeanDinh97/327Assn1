package com.example.a327lab1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a327lab1.models.Music;

import java.util.ArrayList;

public class MusicListFragment extends Fragment {

    public static final String TAG = "MusicListFragment";
    public static final int pageSize = 19;

    private MusicJSONProcessor musicJSONProcessor;

    private RecyclerView recyclerView;

    private TextView pageNumber;
    private ImageView leftBtn;
    private ImageView rightBtn;

    private ArrayList<Music> musicList;
    private ArrayList<Music> musicPageList;
    private int pageIndex;
    private String userName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_list, container, false);

        musicJSONProcessor = new MusicJSONProcessor(getContext());

        initAttributes();
        initUIViews(view);
        initRecyclerView(view);
        updateRecyclerView(view);

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pageIndex > 0) {
                    pageIndex -= pageSize + 1;
                    musicPageList = new ArrayList<Music>(musicList.subList(pageIndex, pageIndex + pageSize));
                    updateRecyclerView(view);
                    updatePageNumberView();
                }
            }
        });

        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pageIndex + pageSize < musicList.size()) {
                    pageIndex += pageSize + 1;
                    musicPageList = new ArrayList<Music>(musicList.subList(pageIndex, pageIndex + pageSize));
                    updateRecyclerView(view);
                    updatePageNumberView();
                }
            }
        });

        return view;
    }

    private void initAttributes() {
        userName = getActivity().getIntent().getExtras().getString("userName");
        musicList = musicJSONProcessor.getListOfMusic();
        musicPageList = new ArrayList<Music>(musicList.subList(pageIndex, pageIndex + 19));
        pageIndex = 0;
    }

    private void initUIViews(View view) {
        pageNumber = view.findViewById(R.id.tv_page_number);
        leftBtn = view.findViewById(R.id.iv_left_page);
        rightBtn = view.findViewById(R.id.iv_right_page);
    }

    private void initRecyclerView(View view) {
        Log.d(TAG, "initRecyclerView: initRecyclerView");

        recyclerView = view.findViewById(R.id.recycler_view_music_list);

    }

    private void updateRecyclerView(View view) {
        MusicListAdapter adapter = new MusicListAdapter(getContext(), musicPageList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager((new LinearLayoutManager(getContext())));
    }

    private void updatePageNumberView() {
        int endPageNumber = pageIndex + pageSize + 1;
        int startPageNumber = pageIndex + 1;
        String pageNumberString = startPageNumber + " - " + endPageNumber;
        pageNumber.setText(pageNumberString);
    }

}
