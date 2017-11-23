package com.example.android.mediaplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pavlo Kotelnytskyi on 8/22/2017.
 */

public class TrackAdapter extends ArrayAdapter<Track> {
    public TrackAdapter(@NonNull Context context, @NonNull List<Track> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.track_list_item, parent, false);
        }

        Track track = getItem(position);

        TextView trackView = (TextView) v.findViewById(R.id.track_list_text);
        trackView.setText(track.getAuthor() + " - " + track.getName());

        return v;
    }
}
