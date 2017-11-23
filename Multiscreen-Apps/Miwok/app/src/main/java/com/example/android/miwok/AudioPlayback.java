package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

/**
 * Created by Pavlo Kotelnytskyi on 8/23/2017.
 */

public class AudioPlayback implements AdapterView.OnItemClickListener {
    private MediaPlayer mp;
    private Context ct;
    private ArrayList<Word> resourceID;
    private AudioManager audioManager;

    private int audioFocusState;

    /**
     * Listens for OnAudioFocusChangeListener and behaves accordingly
     */
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
                            releaseMediaPlayer();
                            audioFocusState = 0;
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

    public AudioPlayback(Context context, ArrayList<Word> audioResourceID) {
        ct = context;
        resourceID = audioResourceID;
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    /**
     * Requests Audio Focus
     *
     * @return Request result
     */
    private boolean requestAudioFocus() {
        if (audioManager.requestAudioFocus(audioChangeListener, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Plays sound. Creating Media Player, onCompletionListener realisation
     *
     * @param resID Sound resource ID to be played
     */
    private void playSound(int resID) {
        if (mp != null) {
            releaseMediaPlayer();
        }

        mp = MediaPlayer.create(ct, resourceID.get(resID).getAudioResourceID());

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                audioManager.abandonAudioFocus(audioChangeListener);
                releaseMediaPlayer();
            }
        });

        mp.start();
    }

    /**
     * Releases Media Player resources
     */
    public void releaseMediaPlayer() {
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

    /**
     * Listener for ListView item onItemClick event.
     * Requests Audio Focus, plays appropriate sound if granted
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (requestAudioFocus()) {
            playSound(i);
        }
    }
}
