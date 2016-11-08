package com.mozawa.wanikaniandroid.ui.dashboard;


import android.util.Log;

import com.mozawa.wanikaniandroid.data.DataManager;
import com.mozawa.wanikaniandroid.data.model.CriticalItem;
import com.mozawa.wanikaniandroid.data.model.StudyQueue;
import com.mozawa.wanikaniandroid.ui.base.BasePresenter;
import com.mozawa.wanikaniandroid.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DashboardPresenter extends BasePresenter<DashboardMvpView> {

    public String TAG = DashboardPresenter.class.getSimpleName();

    private DataManager dataManager;
    private Subscription studyQueueSubscription;
    private Subscription criticalItemsSubscription;

    @Inject
    public DashboardPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(DashboardMvpView mvpView) {
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
        RxUtil.unsubscribe(studyQueueSubscription);
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
        RxUtil.unsubscribe(criticalItemsSubscription);
        criticalItemsSubscription = dataManager.getCriticalItems()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<CriticalItem>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(List<CriticalItem> criticalItemList) {
                        getMvpView().showCriticalItems(criticalItemList);
                    }
                });
    }
}
