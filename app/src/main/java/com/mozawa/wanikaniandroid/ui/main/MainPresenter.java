package com.mozawa.wanikaniandroid.ui.main;

import android.util.Log;

import com.mozawa.wanikaniandroid.data.WaniKaniManager;
import com.mozawa.wanikaniandroid.data.WaniKaniService;
import com.mozawa.wanikaniandroid.data.model.StudyQueue;
import com.mozawa.wanikaniandroid.ui.base.BasePresenter;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainMvpView> {

    private WaniKaniManager waniKaniManager;
    private Subscription subscription;

    public MainPresenter(WaniKaniManager waniKaniManager) {
        this.waniKaniManager = waniKaniManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
        super.detachView();
    }

    public void loadStudyQueue() {
        checkViewAttached();

        WaniKaniService service = waniKaniManager.getService();
        subscription = service.getStudyQueue()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<StudyQueue>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("momo", e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(StudyQueue studyQueue) {
                        getMvpView().showStudyQueue(studyQueue);
                    }
                });
    }
}


