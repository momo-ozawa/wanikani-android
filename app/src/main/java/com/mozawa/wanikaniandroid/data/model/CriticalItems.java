package com.mozawa.wanikaniandroid.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CriticalItems {
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
                "type": "radical",
                "character": "二",
                "meaning": "two",
                "image": null,
                "level": 1,
                "percentage": "80"
            }
        ]
    }
    */

    @SerializedName("user_information")
    public UserInformation userInformation;
    @SerializedName("requested_information")
    public List<CriticalItemInformation> criticalItemsInformationList;

    public static class CriticalItemInformation {

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

}
