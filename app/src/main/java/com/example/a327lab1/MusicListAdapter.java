package com.example.a327lab1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a327lab1.models.Music;

import java.util.ArrayList;

public class MusicListAdapter extends BaseAdapter {

    private ArrayList<Music> listData;
    private LayoutInflater layoutInflater;

    public MusicListAdapter(Context aContext, ArrayList<Music> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_layout, null);
            holder = new ViewHolder();
            holder.musicTitleView = (TextView) convertView.findViewById(R.id.songTitle);
            holder.musicArtistView = (TextView) convertView.findViewById(R.id.songArtist);
            holder.musicDateView = (TextView) convertView.findViewById(R.id.songDate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.musicTitleView.setText(listData.get(position).getSongTitle());
        holder.musicArtistView.setText("By, " + listData.get(position).getArtistName());
        holder.musicDateView.setText(listData.get(position).getReleaseDate());
        return convertView;
    }

    static class ViewHolder {
        TextView musicTitleView;
        TextView musicArtistView;
        TextView musicDateView;
    }
}
