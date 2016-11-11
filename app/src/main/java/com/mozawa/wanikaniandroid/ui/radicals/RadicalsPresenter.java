package com.mozawa.wanikaniandroid.ui.radicals;


import com.mozawa.wanikaniandroid.data.DataManager;
import com.mozawa.wanikaniandroid.data.model.Radical;
import com.mozawa.wanikaniandroid.ui.base.BasePresenter;
import com.mozawa.wanikaniandroid.util.RxUtil;

import java.util.List;

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

        getMvpView().showProgressBar(true);

        radicalsSubscription = dataManager.getRadicals()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Radical>>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().showProgressBar(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showProgressBar(false);
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Radical> radicalList) {
                        if (radicalList.size() > 0) {
                            getMvpView().showRadicals(radicalList);
                        } else {
                            getMvpView().showRadicalsEmpty();
                        }
                    }
                });
    }
}
