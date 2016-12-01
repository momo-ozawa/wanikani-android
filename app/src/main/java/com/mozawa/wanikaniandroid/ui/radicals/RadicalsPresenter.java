package com.mozawa.wanikaniandroid.ui.radicals;


import com.mozawa.wanikaniandroid.data.DataManager;
import com.mozawa.wanikaniandroid.data.model.ListHeader;
import com.mozawa.wanikaniandroid.data.model.ListItem;
import com.mozawa.wanikaniandroid.data.model.Radical;
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

        radicalsSubscription = getRadicalsGroupedByLevel()
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
                            getMvpView().showListItemsEmpty();
                        }
                    }
                });
    }

    private Observable<List<ListItem>> getRadicalsGroupedByLevel() {
        return dataManager.getRadicals()
                .flatMap(new Func1<List<Radical>, Observable<Radical>>() {
                    @Override
                    public Observable<Radical> call(List<Radical> radicalList) {
                        return Observable.from(radicalList);
                    }
                })
                .groupBy(new Func1<Radical, Integer>() {
                    @Override
                    public Integer call(Radical radical) {
                        return radical.level;
                    }
                })
                .flatMap(new Func1<GroupedObservable<Integer, Radical>, Observable<List<ListItem>>>() {
                    @Override
                    public Observable<List<ListItem>> call(final GroupedObservable<Integer, Radical> groupedObservable) {
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

    private Observable<List<ListItem>> getLevelList(final GroupedObservable<Integer, Radical> groupedObservable) {
        return groupedObservable.toList()
                .map(new Func1<List<Radical>, List<ListItem>>() {
                    @Override
                    public List<ListItem> call(List<Radical> radicalList) {
                        List<ListItem> list = new LinkedList<>();
                        list.add(new ListHeader("Level " + groupedObservable.getKey()));
                        list.addAll(radicalList);
                        return list;
                    }
                });
    }

    private Observable<List<ListItem>> getRadicalsGroupedBySrs() {
        return dataManager.getRadicals()
                .flatMap(new Func1<List<Radical>, Observable<Radical>>() {
                    @Override
                    public Observable<Radical> call(List<Radical> radicalList) {
                        return Observable.from(radicalList);
                    }
                })
                .groupBy(new Func1<Radical, String>() {
                    @Override
                    public String call(Radical radical) {
                        return radical.userSpecific.srs;
                    }
                })
                .flatMap(new Func1<GroupedObservable<String, Radical>, Observable<List<ListItem>>>() {
                    @Override
                    public Observable<List<ListItem>> call(GroupedObservable<String, Radical> groupedObservable) {
                        return getSrsList(groupedObservable);
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

    private Observable<List<ListItem>> getSrsList(final GroupedObservable<String, Radical> groupedObservable) {
        return groupedObservable.toList()
                .map(new Func1<List<Radical>, List<ListItem>>() {
                    @Override
                    public List<ListItem> call(List<Radical> radicalList) {
                        List<ListItem> list = new LinkedList<>();
                        list.add(new ListHeader(groupedObservable.getKey()));
                        list.addAll(radicalList);
                        return list;
                    }
                });
    }
}
