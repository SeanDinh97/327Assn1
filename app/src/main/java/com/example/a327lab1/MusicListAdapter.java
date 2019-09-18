package com.example.a327lab1;

import android.content.Context;
import android.content.Intent;
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

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Shows the recycle view of the music list.
 */
public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private static final String TAG = "MusicListAdapter";
    private ArrayList<Music> listOfMusic;
    private String userName;
    private Context context;

    public MusicListAdapter(Context context, ArrayList<Music> listOfMusic, String userName) {
        this.listOfMusic = listOfMusic;
        this.userName = userName;
        this.context = context;
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

                Toast.makeText(context, listOfMusic.get(position).getSongTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //Open menu: 1.Remove song from playlist
                return false;
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
                        Toast.makeText(context, "Navigate to Add song to playlist screen", Toast.LENGTH_SHORT).show();
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
