package com.mozawa.wanikaniandroid.ui.vocabulary;


import com.mozawa.wanikaniandroid.data.DataManager;
import com.mozawa.wanikaniandroid.data.model.Vocabulary;
import com.mozawa.wanikaniandroid.ui.base.BasePresenter;
import com.mozawa.wanikaniandroid.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class VocabularyPresenter extends BasePresenter<VocabularyMvpView> {

    public String TAG = VocabularyPresenter.class.getSimpleName();

    private DataManager dataManager;
    private Subscription vocabularySubscription;

    @Inject
    public VocabularyPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(VocabularyMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {

        if (vocabularySubscription != null) {
            vocabularySubscription.unsubscribe();
        }
        super.detachView();
    }

    public void loadVocabulary() {
        checkViewAttached();
        RxUtil.unsubscribe(vocabularySubscription);

        getMvpView().showProgressBar(true);

        vocabularySubscription = dataManager.getVocabulary()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Vocabulary>>() {
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
                    public void onNext(List<Vocabulary> vocabularyList) {
                        if (vocabularyList.size() > 0) {
                            getMvpView().showVocabulary(vocabularyList);
                        } else {
                            getMvpView().showVocabularyEmpty();
                        }
                    }
                });
    }
}
