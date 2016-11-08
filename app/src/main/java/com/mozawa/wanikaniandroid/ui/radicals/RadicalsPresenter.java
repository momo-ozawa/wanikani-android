package com.mozawa.wanikaniandroid.ui.radicals;


import android.util.Log;

import com.mozawa.wanikaniandroid.data.DataManager;
import com.mozawa.wanikaniandroid.data.model.Radicals;
import com.mozawa.wanikaniandroid.ui.base.BasePresenter;
import com.mozawa.wanikaniandroid.util.RxUtil;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RadicalsPresenter extends BasePresenter<RadicalsMvpView> {

    public String TAG = RadicalsPresenter.class.getSimpleName();

    private DataManager dataManager;
    private Subscription radicalsSubscription;

    @Inject
    public RadicalsPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(RadicalsMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {

        if (radicalsSubscription != null) {
            radicalsSubscription.unsubscribe();
        }

        super.detachView();
    }

    public void loadRadicals() {
        checkViewAttached();
        RxUtil.unsubscribe(radicalsSubscription);
        radicalsSubscription = dataManager.getRadicals()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Radicals>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(Radicals radicals) {
                        getMvpView().showRadicals(radicals);
                    }
                });
    }
}
