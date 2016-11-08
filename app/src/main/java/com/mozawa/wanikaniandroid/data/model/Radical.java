package com.mozawa.wanikaniandroid.data.model;

import com.google.gson.annotations.SerializedName;

public class Radical {

    public String character;
    public String meaning;
    public String image;
    public int level;
    @SerializedName("user_specific")
    public UserSpecific userSpecific;

    public boolean characterExists() {
        return character != null;
    }

}