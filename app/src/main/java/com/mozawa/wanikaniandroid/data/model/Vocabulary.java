package com.mozawa.wanikaniandroid.data.model;

import com.google.gson.annotations.SerializedName;

public class Vocabulary {

    public String character;
    public String kana;
    public String meaning;
    public int level;
    @SerializedName("user_specific")
    public UserSpecific userSpecific;

}
