package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Pavlo Kotelnytskyi on 8/29/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter {
    private static final String LOCATION_SEPARATOR = " of ";

    private ArrayList<Earthquake> earthquakes;

    public EarthquakeAdapter(@NonNull Context context, @NonNull ArrayList<Earthquake> objects) {
        super(context, 0, objects);
        earthquakes = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View root = convertView;

        if (root == null) {
            root = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,
                    parent, false);
        }

        Double mag = getEarthquake(position).getMag();
        TextView magTextView = (TextView) root.findViewById(R.id.magnitude);
        GradientDrawable magCircle = (GradientDrawable) magTextView.getBackground();
        magCircle.setColor(getMagnitudeColor(mag));
        String magString = new DecimalFormat("0.00").format(mag);
        magTextView.setText(String.valueOf(magString));

        String location = getEarthquake(position).getPlace();
        TextView offset = (TextView) root.findViewById(R.id.location_offset);
        TextView primary = (TextView) root.findViewById(R.id.primary_location);

        if (location.contains(LOCATION_SEPARATOR)) {
            String[] split = location.split(LOCATION_SEPARATOR);
            offset.setText(split[0] + LOCATION_SEPARATOR);
            primary.setText(split[1]);
        } else {
            offset.setText(root.getResources().getText(R.string.default_offset_string));
            primary.setText(location);
        }


        TextView date = (TextView) root.findViewById(R.id.date);
        date.setText(getEarthquake(position).getDate());

        TextView time = (TextView) root.findViewById(R.id.time);
        time.setText(getEarthquake(position).getTime());

        return root;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeResID;
        int roundMagnitude = (int) Math.round(magnitude);

        switch (roundMagnitude) {
            case 0:
            case 1:
                magnitudeResID = R.color.magnitude1;
                break;
            case 2:
                magnitudeResID = R.color.magnitude2;
                break;
            case 3:
                magnitudeResID = R.color.magnitude3;
                break;
            case 4:
                magnitudeResID = R.color.magnitude4;
                break;
            case 5:
                magnitudeResID = R.color.magnitude5;
                break;
            case 6:
                magnitudeResID = R.color.magnitude6;
                break;
            case 7:
                magnitudeResID = R.color.magnitude7;
                break;
            case 8:
                magnitudeResID = R.color.magnitude8;
                break;
            case 9:
                magnitudeResID = R.color.magnitude9;
                break;
            default:
                magnitudeResID = R.color.magnitude10plus;
        }

        return ContextCompat.getColor(getContext(), magnitudeResID);
    }

    public Earthquake getEarthquake(int i) {
        return earthquakes.get(i);
    }
}
