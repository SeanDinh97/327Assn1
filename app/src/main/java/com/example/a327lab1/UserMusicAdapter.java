package com.example.a327lab1;

import android.content.Context;
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

import java.util.ArrayList;

public class UserMusicAdapter extends RecyclerView.Adapter<UserMusicAdapter.ViewHolder> {

    private static final String TAG = "UserMusicAdapter";

    private ArrayList<Music> listOfMusic;
    private Context context;

    public UserMusicAdapter(Context context, ArrayList<Music> listOfMusic) {
        this.listOfMusic = listOfMusic;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_music_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserMusicAdapter.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.title.setText(listOfMusic.get(position).getSongTitle());
        holder.artist.setText(listOfMusic.get(position).getArtistName());
        holder.date.setText(listOfMusic.get(position).getReleaseDate());

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener  {
        TextView title;
        TextView artist;
        TextView date;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_user_music_title);
            artist = itemView.findViewById(R.id.tv_user_music_artist);
            date = itemView.findViewById(R.id.tv_user_music_date);
            parentLayout = itemView.findViewById(R.id.parent_layout_user_music);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select The Action");
            MenuItem removeSong = menu.add(1, 1, 1, "Remove Song from Playlist");//groupId, itemId, order, title

            removeSong.setOnMenuItemClickListener(onEditMenu);
        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1:
                        //Implement Remove song from playlist feature
                        Toast.makeText(context, "Implement Remove Song From Playlist function", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        };
    }
}