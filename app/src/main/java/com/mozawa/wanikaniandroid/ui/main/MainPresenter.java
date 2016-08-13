package com.mozawa.wanikaniandroid.ui.main;

import android.util.Log;

import com.mozawa.wanikaniandroid.data.DataManager;
import com.mozawa.wanikaniandroid.data.model.CriticalItems;
import com.mozawa.wanikaniandroid.data.model.StudyQueue;
import com.mozawa.wanikaniandroid.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainMvpView> {

    public String TAG = MainPresenter.class.getSimpleName();

    private DataManager dataManager;
    private Subscription studyQueueSubscription;
    private Subscription criticalItemsSubscription;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {

        if (studyQueueSubscription != null) {
            studyQueueSubscription.unsubscribe();
        }

        if (criticalItemsSubscription != null) {
            criticalItemsSubscription.unsubscribe();
        }

        super.detachView();
    }

    public void loadStudyQueue() {
        checkViewAttached();

        studyQueueSubscription = dataManager.getStudyQueue()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<StudyQueue>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(StudyQueue studyQueue) {
                        getMvpView().showAvailableStudyQueue(studyQueue);
                        getMvpView().showReviewStudyQueue(studyQueue);
                    }
                });
    }

    public void loadCriticalItems() {
        checkViewAttached();

        criticalItemsSubscription = dataManager.getCriticalItems()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CriticalItems>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(CriticalItems criticalItems) {
                        getMvpView().showCriticalItems(criticalItems);
                    }
                });
    }
}


