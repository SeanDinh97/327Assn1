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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Shows the recycle view of the play list.
 */
public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder>{

    private static final String TAG = "PlaylistAdapter";

    private String userName;
    private ArrayList<String> playlistNames;
    private Context context;

    public PlaylistAdapter(String userName, ArrayList<String> playlistNames, Context context) {
        this.userName = userName;
        this.playlistNames = playlistNames;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_playlist_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.name.setText(playlistNames.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to UserPlaylist screen
                Log.d(TAG, "onClick: clicked on: " + playlistNames.get(position));

                String playlistName = playlistNames.get(position);
                Toast.makeText(context, playlistNames.get(position), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(view.getContext(), UserMusicActivity.class);
                i.putExtra("userName", userName);
                i.putExtra("playlistName", playlistName);
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return playlistNames.size();
    }

    public void removeAt(int position) {
        playlistNames.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, playlistNames.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        ImageView image;
        TextView name;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.playlist_image);
            name = itemView.findViewById(R.id.playlist_name);
            parentLayout = itemView.findViewById(R.id.parent_layout_playlist);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select The Action");
            MenuItem rename = menu.add(1, 1, 1, "Rename");//groupId, itemId, order, title
            MenuItem delete = menu.add(1, 2, 2, "Delete");

            rename.setOnMenuItemClickListener(onEditMenu);
            delete.setOnMenuItemClickListener(onEditMenu);
        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case 1:
                        //Implement Rename feature
                        Toast.makeText(context, "Implement Rename function", Toast.LENGTH_SHORT).show();
                        break;

                    case 2:
                        int playlistIndex = getAdapterPosition();
                        String playlistName = playlistNames.get(playlistIndex);
                        UserJSONProcessor userJSONProcessor = new UserJSONProcessor(context);
                        userJSONProcessor.deletePlaylistFromUser(userName,playlistName);
                        removeAt(playlistIndex);
                        break;
                }
                return true;
            }
        };
    }
}
