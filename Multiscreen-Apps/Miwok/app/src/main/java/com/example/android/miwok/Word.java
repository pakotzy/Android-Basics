/**
 * Created by Pavlo Kotelnytskyi on 8/20/2017.
 * <p>
 * Represents a vocabulary word that user wants to learn.
 * It contains a default translation and Miwok translation of the word.
 */
package com.example.android.miwok;

public class Word {
    private static final int NO_IMAGE_PROVIDED = 0;
    private String translation;
    private String miwok;
    private int resourceID;
    private int audioResourceID;

    /**
     * Constructor for Word object. Represents each word in vocabulary without image
     *
     * @param translation Translation on chosen language
     * @param miwok       Miwok native word
     */
    public Word(String translation, String miwok) {
        this.translation = translation;
        this.miwok = miwok;
    }

    /**
     * Constructor for Word object. Represents each word in vocabulary with image
     *
     * @param translation Translation on chosen language
     * @param miwok       Miwok native word
     * @param resourceID  Associanted link to drawable resource
     */
    public Word(String translation, String miwok, int resourceID) {
        this(translation, miwok);
        this.resourceID = resourceID;
    }

    /**
     * Constructor for Word object. Represents each word in vocabulary with image and audio
     *
     * @param translation     Translation on chosen language
     * @param miwok           Miwok native word
     * @param resourceID      Associanted link to drawable resource
     * @param audioResourceID Associated link to audio resource
     */
    public Word(String translation, String miwok, int resourceID, int audioResourceID) {
        this(translation, miwok, resourceID);
        this.audioResourceID = audioResourceID;
    }

    public String getTranslation() {
        return translation;
    }

    public String getMivok() {
        return miwok;
    }

    public int getResourceID() {
        return resourceID;
    }

    public boolean hasImage() {
        return resourceID != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceID() {
        return audioResourceID;
    }
}
