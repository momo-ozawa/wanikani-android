package com.mozawa.wanikaniandroid.data;

import com.mozawa.wanikaniandroid.data.model.StudyQueue;

import retrofit2.http.GET;
import rx.Observable;

public interface WaniKaniService {

    @GET("study-queue")
    Observable<StudyQueue> getStudyQueue();
}
