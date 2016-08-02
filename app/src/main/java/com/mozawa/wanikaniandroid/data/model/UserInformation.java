package com.mozawa.wanikaniandroid.data.model;

import com.google.gson.annotations.SerializedName;

public class UserInformation {

    /*
    "username": "momo-ozawa",
    "gravatar": "26c11682771a8d300ac1b6b225d3e52b",
    "level": 1,
    "title": "Guppies",
    "about": "",
    "website": null,
    "twitter": null,
    "topics_count": 0,
    "posts_count": 0,
    "creation_date": 1462411044,
    "vacation_date": null
    */

    public String username;
    public String gravatar;
    public int level;
    public String title;
    public String about;
    public String website;
    public String twitter;
    @SerializedName("topics_count")
    public int topicsCount;
    @SerializedName("posts_count")
    public int postsCount;
    @SerializedName("creation_date")
    public long creationDate;
    @SerializedName("vacation_date")
    public long vacationDate;
}
