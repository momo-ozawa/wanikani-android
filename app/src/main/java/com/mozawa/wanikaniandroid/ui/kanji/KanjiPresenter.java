package com.mozawa.wanikaniandroid.ui.kanji;


import com.mozawa.wanikaniandroid.data.DataManager;
import com.mozawa.wanikaniandroid.data.model.Kanji;
import com.mozawa.wanikaniandroid.ui.base.BasePresenter;
import com.mozawa.wanikaniandroid.util.RxUtil;

import java.util.List;

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
        RxUtil.unsubscribe(kanjiSubscription);

        getMvpView().showProgressBar(true);

        kanjiSubscription = dataManager.getKanji()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Kanji>>() {
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
                    public void onNext(List<Kanji> kanjiList) {
                        if (kanjiList.size() > 0) {
                            getMvpView().showKanji(kanjiList);
                        } else {
                            getMvpView().showKanjiEmpty();
                        }
                    }
                });
    }
}
