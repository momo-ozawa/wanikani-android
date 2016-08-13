package com.mozawa.wanikaniandroid.data;

import com.mozawa.wanikaniandroid.data.model.CriticalItems;
import com.mozawa.wanikaniandroid.data.model.Radicals;
import com.mozawa.wanikaniandroid.data.model.StudyQueue;

import retrofit2.http.GET;
import rx.Observable;

public interface WaniKaniService {

    @GET("study-queue")
    Observable<StudyQueue> getStudyQueue();

    @GET("critical-items")
    Observable<CriticalItems> getCriticalItems();

    @GET("radicals")
    Observable<Radicals> getRadicals();
}
