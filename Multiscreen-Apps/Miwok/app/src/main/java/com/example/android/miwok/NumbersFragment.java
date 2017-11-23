package com.example.android.miwok;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersFragment extends Fragment {
    private AudioPlayback ap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ListView root = (ListView) inflater.inflate(R.layout.word_list, container, false);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("One", "Lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("Two", "Otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("Three", "Tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("Four", "Oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("Five", "Massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("Six", "Temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("Seven", "Kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("Eight", "Kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("Nine", "Wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("Ten", "Na’aacha", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter wordsAdapter = new WordAdapter(getContext(), words, R.color.category_numbers);
        root.setAdapter(wordsAdapter);
        ap = new AudioPlayback(getContext(), words);
        root.setOnItemClickListener(ap);

        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
        ap.releaseMediaPlayer();
    }
}
