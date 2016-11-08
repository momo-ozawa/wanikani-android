package com.mozawa.wanikaniandroid.data.model;

import com.google.gson.annotations.SerializedName;

public class Kanji {

    public String character;
    public String meaning;
    public String onyomi;
    public String kunyomi;
    public String nanori;
    @SerializedName("important_reading")
    public String importantReading;
    public int level;
    @SerializedName("user_specific")
    public UserSpecific userSpecific;

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setOnyomi(String onyomi) {
        this.onyomi = onyomi;
    }

}