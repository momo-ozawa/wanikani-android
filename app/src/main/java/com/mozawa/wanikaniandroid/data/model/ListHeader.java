package com.mozawa.wanikaniandroid.data.model;

public class ListHeader implements ListItem {

    public static final String TYPE = "header";

    public String title;

    public ListHeader(String title) {
        this.title = title;
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
