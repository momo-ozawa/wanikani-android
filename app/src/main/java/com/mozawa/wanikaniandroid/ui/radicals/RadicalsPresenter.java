package com.mozawa.wanikaniandroid.ui.radicals;


import android.util.Log;

import com.mozawa.wanikaniandroid.data.WaniKaniManager;
import com.mozawa.wanikaniandroid.data.WaniKaniService;
import com.mozawa.wanikaniandroid.data.model.Radicals;
import com.mozawa.wanikaniandroid.ui.base.BasePresenter;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RadicalsPresenter extends BasePresenter<RadicalsMvpView> {

    public String TAG = RadicalsPresenter.class.getSimpleName();

    private WaniKaniManager waniKaniManager;
    private Subscription radicalsSubscription;

    public RadicalsPresenter(WaniKaniManager waniKaniManager) {
        this.waniKaniManager = waniKaniManager;
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

        WaniKaniService service = waniKaniManager.getService();
        radicalsSubscription = service.getRadicals()
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
