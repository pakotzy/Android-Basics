package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pavlo Kotelnytskyi on 8/20/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    private int colorResource;

    public WordAdapter(Context context, ArrayList<Word> words, int colorResource) {
        super(context, 0, words);
        this.colorResource = colorResource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        // Check if the existing view being reused, otherwise inflate the view
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get item that have to be shown
        Word word = getItem(position);

        // Populate current View with new data
        TextView miwok = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwok.setText(word.getMivok());

        TextView translation = (TextView) listItemView.findViewById(R.id.eng_text_view);
        translation.setText(word.getTranslation());

        ImageView image = (ImageView) listItemView.findViewById(R.id.icon_image_view);
        if (word.hasImage()) {
            image.setImageResource(word.getResourceID());
        } else {
            image.setVisibility(View.GONE);
        }

        LinearLayout wordsView = (LinearLayout) listItemView.findViewById(R.id.list_item_plate);
        wordsView.setBackgroundColor(ContextCompat.getColor(getContext(), colorResource));

        // Return updated view
        return listItemView;
    }
}
