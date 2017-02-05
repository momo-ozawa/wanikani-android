package com.mozawa.wanikaniandroid.ui.kanji;


import com.mozawa.wanikaniandroid.data.DataManager;
import com.mozawa.wanikaniandroid.data.model.Kanji;
import com.mozawa.wanikaniandroid.data.model.ListHeader;
import com.mozawa.wanikaniandroid.data.model.ListItem;
import com.mozawa.wanikaniandroid.ui.base.BasePresenter;
import com.mozawa.wanikaniandroid.util.RxUtil;

import java.util.LinkedList;
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
                .subscribe(new Subscriber<List<ListItem>>() {
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
                    public void onNext(List<ListItem> listItems) {
                        if (listItems.size() > 0) {
                            getMvpView().showListItems(listItems);
                        } else {
                            getMvpView().showKanjiEmpty();
                        }
                    }
                });
    }

    private Observable<List<ListItem>> getKanjiGroupedByLevel() {
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
                .flatMap(new Func1<GroupedObservable<Integer, Kanji>, Observable<List<ListItem>>>() {
                    @Override
                    public Observable<List<ListItem>> call(final GroupedObservable<Integer, Kanji> groupedObservable) {
                        return getLevelList(groupedObservable);
                    }
                })
                .flatMap(new Func1<List<ListItem>, Observable<ListItem>>() {
                    @Override
                    public Observable<ListItem> call(List<ListItem> listItems) {
                        return Observable.from(listItems);
                    }
                })
                .toList();
    }

    private Observable<List<ListItem>> getLevelList(final GroupedObservable<Integer, Kanji> groupedObservable) {
        return groupedObservable.toList()
                .map(new Func1<List<Kanji>, List<ListItem>>() {
                    @Override
                    public List<ListItem> call(List<Kanji> kanjiList) {
                        List<ListItem> list = new LinkedList<>();
                        list.add(new ListHeader("level " + groupedObservable.getKey()));
                        list.addAll(kanjiList);
                        return list;
                    }
                });
    }
}
