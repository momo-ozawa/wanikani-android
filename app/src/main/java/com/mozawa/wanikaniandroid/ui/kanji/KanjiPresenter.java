package com.mozawa.wanikaniandroid.ui.kanji;


import com.mozawa.wanikaniandroid.data.DataManager;
import com.mozawa.wanikaniandroid.data.model.Kanji;
import com.mozawa.wanikaniandroid.ui.base.BasePresenter;
import com.mozawa.wanikaniandroid.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
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

        kanjiSubscription = getKanjiGroupedByLevel()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<List<Kanji>>>() {
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
                    public void onNext(List<List<Kanji>> kanjiGroupedByLevel) {
                        if (kanjiGroupedByLevel.size() > 0) {
                            getMvpView().showKanji(kanjiGroupedByLevel.get(0));
                        } else {
                            getMvpView().showKanjiEmpty();
                        }
                    }
                });
    }


    private Observable<List<List<Kanji>>> getKanjiGroupedByLevel() {
        return dataManager.getKanji()
                .flatMap(new Func1<List<Kanji>, Observable<Kanji>>() {
                    @Override
                    public Observable<Kanji> call(List<Kanji> kanjiList) {
                        return Observable.from(kanjiList);
                    }
                })
                .groupBy(new Func1<Kanji, Integer>() {
                    @Override
                    public Integer call(Kanji kanji) {
                        return kanji.level;
                    }
                })
                .flatMap(new Func1<GroupedObservable<Integer, Kanji>, Observable<List<Kanji>>>() {
                    @Override
                    public Observable<List<Kanji>> call(GroupedObservable<Integer, Kanji> groupedObservable) {
                        return groupedObservable.toList();
                    }
                })
                .toList();
    }
}
