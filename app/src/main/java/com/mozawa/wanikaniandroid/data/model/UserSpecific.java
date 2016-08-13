package com.mozawa.wanikaniandroid.data.model;


import com.google.gson.annotations.SerializedName;

public class UserSpecific {

    /*
    "user_specific": {
        "srs": "guru",
        "srs_numeric": 6,
        "unlocked_date": 1462411044,
        "available_date": 1472232600,
        "burned": false,
        "burned_date": 0,
        "meaning_correct": 5,
        "meaning_incorrect": 0,
        "meaning_max_streak": 5,
        "meaning_current_streak": 5,
        "reading_correct": null,
        "reading_incorrect": null,
        "reading_max_streak": null,
        "reading_current_streak": null,
        "meaning_note": null,
        "user_synonyms": null
    }
    */

    public String srs;
    @SerializedName("srs_numeric")
    public int srsNumeric;
    @SerializedName("unlocked_date")
    public long unlockedDate;
    @SerializedName("available_date")
    public long availableDate;
    public boolean burned;
    @SerializedName("burned_date")
    public long burnedDate;
    @SerializedName("meaning_correct")
    public int meaningCorrect;
    @SerializedName("meaning_incorrect")
    public int meaningIncorrect;
    @SerializedName("meaning_max_streak")
    public int meaningMaxStreak;
    @SerializedName("meaning_current_steak")
    public int meaningCurrentStreak;
    @SerializedName("reading_correct")
    public int readingCorrect;
    @SerializedName("reading_incorrect")
    public int readingIncorrect;
    @SerializedName("reading_max_streak")
    public int readingMaxStreak;
    @SerializedName("reading_current_streak")
    public int readingCurrentStreak;
    @SerializedName("meaning_note")
    public String meaningNote;
    @SerializedName("user_synonyms")
    public String userSynonyms;
}
