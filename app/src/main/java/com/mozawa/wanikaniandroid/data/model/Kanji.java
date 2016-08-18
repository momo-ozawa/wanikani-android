package com.mozawa.wanikaniandroid.data.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Kanji {

    /*
    {
      "user_information": {
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
      },
      "requested_information": [
        {
          "character": "口",
          "meaning": "mouth",
          "onyomi": "こう, く",
          "kunyomi": "くち",
          "nanori": null,
          "important_reading": "onyomi",
          "level": 1,
          "user_specific": null
        }
      ]
    }
    */

    @SerializedName("user_information")
    public UserInformation userInformation;
    @SerializedName("requested_information")
    public List<Kanji.KanjiInformation> kanjiInformationList;

    public static class KanjiInformation {

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

    }
}
