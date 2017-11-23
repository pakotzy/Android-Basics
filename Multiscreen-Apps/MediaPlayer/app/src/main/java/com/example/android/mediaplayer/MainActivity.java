package com.example.android.mediaplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mp;
    private int selectedTrack;
    private ArrayList<Track> playlist = new ArrayList<>();

    private static AudioManager audioManager;
    private static int audioFocusState;

    private AudioManager.OnAudioFocusChangeListener audioChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int i) {
                    switch (i) {
                        case AudioManager.AUDIOFOCUS_GAIN: {
                            if (audioFocusState == 2 && mp != null) {
                                mp.start();
                            }
                            audioFocusState = 1;
                            break;
                        }
                        case AudioManager.AUDIOFOCUS_LOSS: {
                            if (mp != null) {
                                mp.pause();
                                audioFocusState = 2;
                            }
                            break;
                        }
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT: {
                            if (mp != null) {
                                mp.pause();
                                audioFocusState = 2;
                            }
                            break;
                        }
                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK: {
                            if (mp != null) {
                                mp.pause();
                                audioFocusState = 2;
                            }
                            break;
                        }
                        default:
                            break;
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        playlist.add(new Track("Jay-Z", "The Story of O.J.", R.raw.the_story_of_oj));
        playlist.add(new Track("Jay-Z", "4:44", R.raw.four));

        TrackAdapter playlistAdapter = new TrackAdapter(this, playlist);
        final Spinner spinner = (Spinner) findViewById(R.id.playlist_spinner);
        spinner.setAdapter(playlistAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTrack = i;

                if (mp == null) {
                    return;
                }

                startPlayback(playlist.get(selectedTrack).getResID());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void playOnClick(View view) {
        Button play = (Button) findViewById(R.id.play_button);

        if (mp == null) {
            if (requestAudioFocus()) {
                startPlayback(playlist.get(selectedTrack).getResID());
            } else {
                return;
            }
        }

        if (mp.isPlaying()) {
            mp.pause();
            play.setText("Play");
        } else {
            mp.start();
            play.setText("Pause");
        }
    }

    private void startPlayback(int audioResID) {
        if (mp != null) {
            mp.reset();
        }
        mp = MediaPlayer.create(MainActivity.this, playlist.get(selectedTrack).getResID());
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (selectedTrack+1 == playlist.size()) {
                    selectedTrack = 0;
                } else {
                    selectedTrack += 1;
                }

                Spinner list = (Spinner) findViewById(R.id.playlist_spinner);
                list.setSelection(selectedTrack, true);

                startPlayback(playlist.get(selectedTrack).getResID());
            }
        });
        mp.start();
    }

    private boolean requestAudioFocus() {
        if (audioManager.requestAudioFocus(audioChangeListener, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
}
