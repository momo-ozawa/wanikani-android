package com.mozawa.wanikaniandroid.data.model;

import com.google.gson.annotations.SerializedName;

public class StudyQueue {

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
        "requested_information": {
            "lessons_available": 16,
            "reviews_available": 10,
            "next_review_date": 1470108924,
            "reviews_available_next_hour": 0,
            "reviews_available_next_day": 0
        }
    }
    */

    @SerializedName("user_information")
    public UserInformation userInformation;
    @SerializedName("requested_information")
    public StudyQueueInformation studyQueueInformation;

    public static class StudyQueueInformation {

        @SerializedName("lessons_available")
        public int lessonsAvailable;
        @SerializedName("reviews_available")
        public int reviewsAvailable;
        @SerializedName("next_review_date")
        public long nextReviewDate;
        @SerializedName("reviews_available_next_hour")
        public int reviewsAvailableNextHour;
        @SerializedName("reviews_available_next_day")
        public int reviewsAvailableNextDay;
    }
}
