package com.mozawa.wanikaniandroid.data.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Radicals {

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
          "character": null,
          "meaning": "stick",
          "image": "https://s3.amazonaws.com/s3.wanikani.com/images/radicals/802e9542627291d4282601ded41ad16ce915f60f.png",
          "level": 1,
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
        }
      ]
    }
    */

    @SerializedName("user_information")
    public UserInformation userInformation;
    @SerializedName("requested_information")
    public List<RadicalInformation> radicalInformationList;

    public static class RadicalInformation {

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
}
