package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int scoreTeamA = 0;
    int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Updates team A score
     *
     * @param scored
     */
    public void updateTeamAScore(int scored) {
        TextView scoreView = (TextView) findViewById(R.id.scoreAText);
        scoreTeamA += scored;
        scoreView.setText(String.valueOf(scoreTeamA));
    }

    /**
     * On click handler for team A +3 points button
     *
     * @param view
     */
    public void threeAClick(View view) {
        updateTeamAScore(3);
    }

    /**
     * On click handler for team A +2 points button
     *
     * @param view
     */
    public void twoAClick(View view) {
        updateTeamAScore(2);
    }

    /**
     * On click handler for team A Free Throw button
     *
     * @param view
     */
    public void freeAClick(View view) {
        updateTeamAScore(1);
    }

    /**
     * Updates team B score
     *
     * @param scored
     */
    public void updateTeamBScore(int scored) {
        TextView scoreView = (TextView) findViewById(R.id.scoreBText);
        scoreTeamB += scored;
        scoreView.setText(String.valueOf(scoreTeamB));
    }

    /**
     * On click handler for team B +3 points button
     *
     * @param view
     */
    public void threeBClick(View view) {
        updateTeamBScore(3);
    }

    /**
     * On click handler for team B +2 points button
     *
     * @param view
     */
    public void twoBClick(View view) {
        updateTeamBScore(2);
    }

    /**
     * On click handler for team B Free Throw button
     *
     * @param view
     */
    public void freeBClick(View view) {
        updateTeamBScore(1);
    }


    public void resetClick(View view) {
        updateTeamAScore(-1 * scoreTeamA);
        updateTeamBScore(-1 * scoreTeamB);
    }
}
