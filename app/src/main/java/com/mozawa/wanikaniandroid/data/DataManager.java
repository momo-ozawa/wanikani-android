package com.mozawa.wanikaniandroid.data;

import com.mozawa.wanikaniandroid.data.local.PreferencesHelper;
import com.mozawa.wanikaniandroid.data.model.CriticalItems;
import com.mozawa.wanikaniandroid.data.model.Kanji;
import com.mozawa.wanikaniandroid.data.model.Radicals;
import com.mozawa.wanikaniandroid.data.model.StudyQueue;
import com.mozawa.wanikaniandroid.data.remote.WaniKaniService;

import javax.inject.Inject;

import rx.Observable;

public class DataManager {

    private final WaniKaniService waniKaniService;
    private final PreferencesHelper preferencesHelper;

    @Inject
    public DataManager(WaniKaniService waniKaniService, PreferencesHelper preferencesHelper) {
        this.waniKaniService = waniKaniService;
        this.preferencesHelper = preferencesHelper;
    }

    public Observable<StudyQueue> getStudyQueue() {
        return waniKaniService.getStudyQueue();
    }

    public Observable<CriticalItems> getCriticalItems() {
        return waniKaniService.getCriticalItems();
    }

    public Observable<Radicals> getRadicals() {
        return waniKaniService.getRadicals();
    }

    public Observable<Kanji> getKanji() {
        return waniKaniService.getKanji();
    }
}
