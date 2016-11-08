package com.mozawa.wanikaniandroid.ui.main;

import com.mozawa.wanikaniandroid.data.DataManager;
import com.mozawa.wanikaniandroid.ui.base.BasePresenter;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainMvpView> {

    public String TAG = MainPresenter.class.getSimpleName();

    private DataManager dataManager;

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
        super.detachView();
    }

}


