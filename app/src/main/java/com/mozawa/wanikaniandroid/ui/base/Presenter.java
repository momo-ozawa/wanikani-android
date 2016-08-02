package com.mozawa.wanikaniandroid.ui.base;

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);
    void detachView();

}
