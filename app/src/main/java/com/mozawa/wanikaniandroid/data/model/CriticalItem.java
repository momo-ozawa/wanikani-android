package com.mozawa.wanikaniandroid.data.model;

public class CriticalItem {

    public String type;
    public String character;
    public String meaning;
    public String image;
    public int level;
    public String percentage;

    public String getCharacter() {
        if (character != null) {
            return character;
        }
        return image;
    }

}
