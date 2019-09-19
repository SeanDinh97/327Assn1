package com.example.a327lab1;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a327lab1.models.Music;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Shows the recycle view of the music list.
 */
public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private static final String TAG = "MusicListAdapter";
    private MediaPlayer mp;
    private ArrayList<Music> listOfMusic;
    private String userName;
    private Context context;
    private String currentlyPlaying;

    public MusicListAdapter(Context context, ArrayList<Music> listOfMusic, String userName) {
        this.listOfMusic = listOfMusic;
        this.userName = userName;
        this.context = context;
        this.currentlyPlaying = "";
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_music_item, parent, false);
        MusicListAdapter.ViewHolder holder = new MusicListAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MusicListAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.title.setText(listOfMusic.get(position).getSongTitle());
        holder.artist.setText(listOfMusic.get(position).getArtistName());
        holder.id.setText(listOfMusic.get(position).getRelease().getId());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Play the music!
                Log.d(TAG, "onClick: clicked on: " + listOfMusic.get(position).getSongTitle());

                if (mp != null && !currentlyPlaying.equals(listOfMusic.get(position).getSongTitle())) {
                    mp.stop();
                    mp.release();
                    mp = MediaPlayer.create(context,R.raw.imperial);
                    mp.start();
                    currentlyPlaying = listOfMusic.get(position).getSongTitle();
                    Toast.makeText(context, currentlyPlaying + " is now playing.", Toast.LENGTH_SHORT).show();
                } else if (mp != null){
                    mp.stop();
                    mp.release();
                    mp = null;
                    Toast.makeText(context, "Stopped playing " + currentlyPlaying, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, listOfMusic.get(position).getSongTitle() + " is now playing.", Toast.LENGTH_SHORT).show();
                    mp = MediaPlayer.create(context,R.raw.imperial);
                    mp.start();
                    currentlyPlaying = listOfMusic.get(position).getSongTitle();
                    Toast.makeText(context, "Stopped playing " + currentlyPlaying, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfMusic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView title;
        TextView artist;
        TextView id;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_music_title);
            artist = itemView.findViewById(R.id.tv_music_artist);
            id = itemView.findViewById(R.id.tv_music_id);
            parentLayout = itemView.findViewById(R.id.parent_layout_music);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select The Action");
            MenuItem addSong = menu.add(1, 1, 1, "Add Song to Playlist");//groupId, itemId, order, title

            addSong.setOnMenuItemClickListener(onEditMenu);
        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1:
                        //Implement Add song to Playlist Feature
                        Intent i = new Intent(context, AddMusicToPlaylistActivity.class);
                        i.putExtra("userName", userName);
                        i.putExtra("music", listOfMusic.get(getLayoutPosition()));
                        context.startActivity(i);
                        break;
                }
                return true;
            }
        };
    }
}
