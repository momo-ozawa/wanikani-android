package com.mozawa.wanikaniandroid;

import com.mozawa.wanikaniandroid.data.DataManager;
import com.mozawa.wanikaniandroid.data.model.Radical;
import com.mozawa.wanikaniandroid.ui.radicals.RadicalsMvpView;
import com.mozawa.wanikaniandroid.ui.radicals.RadicalsPresenter;
import com.mozawa.wanikaniandroid.util.RxSchedulersOverrideRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import sharedTest.TestDataFactory;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RadicalsPresenterTest {

    @Mock
    RadicalsMvpView mockRadicalsMvpView;

    @Mock
    DataManager mockDataManager;

    private RadicalsPresenter radicalsPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        radicalsPresenter = new RadicalsPresenter(mockDataManager);
        radicalsPresenter.attachView(mockRadicalsMvpView);
    }

    @After
    public void tearDown() {
        radicalsPresenter.detachView();
    }

    @Test
    public void loadRadicalsReturnsRadicals() {
        List<Radical> radicalList = TestDataFactory.makeRadicalList(10);
        when(mockDataManager.getRadicals())
                .thenReturn(Observable.just(radicalList));

        radicalsPresenter.loadRadicals();
        verify(mockRadicalsMvpView, times(1)).showRadicals(radicalList);
        verify(mockRadicalsMvpView, times(1)).showProgressBar(true);
        verify(mockRadicalsMvpView, times(1)).showProgressBar(false);
        verify(mockRadicalsMvpView, never()).showError();
        verify(mockRadicalsMvpView, never()).showRadicalsEmpty();
    }

    @Test
    public void loadRadicalsReturnsEmptyList() {
        when(mockDataManager.getRadicals())
                .thenReturn(Observable.just(Collections.<Radical>emptyList()));

        radicalsPresenter.loadRadicals();
        verify(mockRadicalsMvpView, times(1)).showRadicalsEmpty();
        verify(mockRadicalsMvpView, times(1)).showProgressBar(true);
        verify(mockRadicalsMvpView, times(1)).showProgressBar(false);
        verify(mockRadicalsMvpView, never()).showRadicals(anyListOf(Radical.class));
        verify(mockRadicalsMvpView, never()).showError();
    }

    @Test
    public void loadRadicalsFails() {
        when(mockDataManager.getRadicals())
                .thenReturn(Observable.<List<Radical>>error(new RuntimeException()));

        radicalsPresenter.loadRadicals();
        verify(mockRadicalsMvpView, times(1)).showError();
        verify(mockRadicalsMvpView, times(1)).showProgressBar(true);
        verify(mockRadicalsMvpView, times(1)).showProgressBar(false);
        verify(mockRadicalsMvpView, never()).showRadicalsEmpty();
        verify(mockRadicalsMvpView, never()).showRadicals(anyListOf(Radical.class));
    }

}
