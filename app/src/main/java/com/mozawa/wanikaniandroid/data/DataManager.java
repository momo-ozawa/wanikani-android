package com.mozawa.wanikaniandroid.data;

import com.mozawa.wanikaniandroid.data.local.PreferencesHelper;
import com.mozawa.wanikaniandroid.data.model.CriticalItem;
import com.mozawa.wanikaniandroid.data.model.CriticalItemResponse;
import com.mozawa.wanikaniandroid.data.model.Kanji;
import com.mozawa.wanikaniandroid.data.model.KanjiResponse;
import com.mozawa.wanikaniandroid.data.model.Radical;
import com.mozawa.wanikaniandroid.data.model.RadicalResponse;
import com.mozawa.wanikaniandroid.data.model.StudyQueue;
import com.mozawa.wanikaniandroid.data.model.StudyQueueResponse;
import com.mozawa.wanikaniandroid.data.model.Vocabulary;
import com.mozawa.wanikaniandroid.data.model.VocabularyReponse;
import com.mozawa.wanikaniandroid.data.remote.WaniKaniService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class DataManager {

    private final WaniKaniService waniKaniService;
    private final PreferencesHelper preferencesHelper;

    @Inject
    public DataManager(WaniKaniService waniKaniService, PreferencesHelper preferencesHelper) {
        this.waniKaniService = waniKaniService;
        this.preferencesHelper = preferencesHelper;
    }

    public Observable<StudyQueue> getStudyQueue() {
        return waniKaniService.getStudyQueue()
                .flatMap(new Func1<StudyQueueResponse, Observable<StudyQueue>>() {
                    @Override
                    public Observable<StudyQueue> call(StudyQueueResponse response) {
                        return Observable.just(response.studyQueue);
                    }
                });
    }

    public Observable<List<CriticalItem>> getCriticalItems() {
        return waniKaniService.getCriticalItems()
                .flatMap(new Func1<CriticalItemResponse, Observable<List<CriticalItem>>>() {
                    @Override
                    public Observable<List<CriticalItem>> call(CriticalItemResponse response) {
                        return Observable.just(response.criticalItemList);
                    }
                });
    }

    public Observable<List<Radical>> getRadicals() {
        return waniKaniService.getRadicals()
                .flatMap(new Func1<RadicalResponse, Observable<List<Radical>>>() {
                    @Override
                    public Observable<List<Radical>> call(RadicalResponse response) {
                        return Observable.just(response.radicalList);
                    }
                });
    }

    public Observable<List<Kanji>> getKanji() {
        return waniKaniService.getKanji()
                .flatMap(new Func1<KanjiResponse, Observable<List<Kanji>>>() {
                    @Override
                    public Observable<List<Kanji>> call(KanjiResponse response) {
                        return Observable.just(response.kanjiList);
                    }
                });
    }

    public Observable<List<Vocabulary>> getVocabulary() {
        return waniKaniService.getVocabulary()
                .flatMap(new Func1<VocabularyReponse, Observable<List<Vocabulary>>>() {
                    @Override
                    public Observable<List<Vocabulary>> call(VocabularyReponse response) {
                        return Observable.just(response.vocabularyList);
                    }
                });
    }
}
