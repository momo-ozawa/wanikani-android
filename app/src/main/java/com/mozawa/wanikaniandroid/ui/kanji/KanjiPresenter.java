package com.mozawa.wanikaniandroid.ui.kanji;


import android.util.Log;

import com.mozawa.wanikaniandroid.data.DataManager;
import com.mozawa.wanikaniandroid.data.model.Kanji;
import com.mozawa.wanikaniandroid.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class KanjiPresenter extends BasePresenter<KanjiMvpView> {

    public String TAG = KanjiPresenter.class.getSimpleName();

    private DataManager dataManager;
    private Subscription kanjiSubscription;

    @Inject
    public KanjiPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(KanjiMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {

        if (kanjiSubscription != null) {
            kanjiSubscription.unsubscribe();
        }
        super.detachView();
    }

    public void loadKanji() {
        checkViewAttached();

        kanjiSubscription = dataManager.getKanji()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Kanji>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(Kanji kanji) {
                        getMvpView().showKanji(kanji);
                        Log.d(TAG, kanji.kanjiInformationList.size() + "");
                    }
                });
    }
}
