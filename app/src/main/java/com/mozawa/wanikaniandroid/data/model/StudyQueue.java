package com.mozawa.wanikaniandroid.data.model;

import com.google.gson.annotations.SerializedName;

public class StudyQueue {

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
