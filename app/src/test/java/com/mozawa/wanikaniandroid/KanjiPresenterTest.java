package com.mozawa.wanikaniandroid;


import com.mozawa.wanikaniandroid.data.DataManager;
import com.mozawa.wanikaniandroid.data.model.Kanji;
import com.mozawa.wanikaniandroid.ui.kanji.KanjiMvpView;
import com.mozawa.wanikaniandroid.ui.kanji.KanjiPresenter;
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
public class KanjiPresenterTest {

    @Mock
    KanjiMvpView mockKanjiMvpView;

    @Mock
    DataManager mockDataManager;

    private KanjiPresenter kanjiPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        kanjiPresenter = new KanjiPresenter(mockDataManager);
        kanjiPresenter.attachView(mockKanjiMvpView);
    }

    @After
    public void tearDown() {
        kanjiPresenter.detachView();
    }

    @Test
    public void loadKanjiReturnsKanji() {
        List<Kanji> kanjiList = TestDataFactory.makeKanjiList(10);
        when(mockDataManager.getKanji())
                .thenReturn(Observable.just(kanjiList));

        kanjiPresenter.loadKanji();
        verify(mockKanjiMvpView, times(1)).showKanji(kanjiList);
        verify(mockKanjiMvpView, times(1)).showProgressBar(true);
        verify(mockKanjiMvpView, times(1)).showProgressBar(false);
        verify(mockKanjiMvpView, never()).showError();
        verify(mockKanjiMvpView, never()).showKanjiEmpty();
    }

    @Test
    public void loadKanjiReturnsEmptyList() {
        when(mockDataManager.getKanji())
                .thenReturn(Observable.just(Collections.<Kanji>emptyList()));

        kanjiPresenter.loadKanji();
        verify(mockKanjiMvpView, times(1)).showKanjiEmpty();
        verify(mockKanjiMvpView, times(1)).showProgressBar(true);
        verify(mockKanjiMvpView, times(1)).showProgressBar(false);
        verify(mockKanjiMvpView, never()).showKanji(anyListOf(Kanji.class));
        verify(mockKanjiMvpView, never()).showError();
    }

    @Test
    public void loadKanjiFails() {
        when(mockDataManager.getKanji())
                .thenReturn(Observable.<List<Kanji>>error(new RuntimeException()));

        kanjiPresenter.loadKanji();
        verify(mockKanjiMvpView, times(1)).showError();
        verify(mockKanjiMvpView, times(1)).showProgressBar(true);
        verify(mockKanjiMvpView, times(1)).showProgressBar(false);
        verify(mockKanjiMvpView, never()).showKanjiEmpty();
        verify(mockKanjiMvpView, never()).showKanji(anyListOf(Kanji.class));
    }
}
