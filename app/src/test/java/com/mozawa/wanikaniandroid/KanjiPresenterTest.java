package com.mozawa.wanikaniandroid;


import com.mozawa.wanikaniandroid.data.DataManager;
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
//        List<Ribot> ribots = TestDataFactory.makeListKanji(10);
//        when(mMockDataManager.getKanji())
//                .thenReturn(Observable.just(ribots));
//
//        mMainPresenter.loadKanji();
//        verify(mMockMainMvpView).showKanji(ribots);
//        verify(mMockMainMvpView, never()).showKanjiEmpty();
//        verify(mMockMainMvpView, never()).showError();
    }

    @Test
    public void loadKanjiReturnsEmptyList() {
//        when(mMockDataManager.getKanji())
//                .thenReturn(Observable.just(Collections.<Ribot>emptyList()));
//
//        mMainPresenter.loadKanji();
//        verify(mMockMainMvpView).showKanjiEmpty();
//        verify(mMockMainMvpView, never()).showKanji(anyListOf(Ribot.class));
//        verify(mMockMainMvpView, never()).showError();
    }

    @Test
    public void loadKanjiFails() {
//        when(mMockDataManager.getKanji())
//                .thenReturn(Observable.<List<Ribot>>error(new RuntimeException()));
//
//        mMainPresenter.loadKanji();
//        verify(mMockMainMvpView).showError();
//        verify(mMockMainMvpView, never()).showKanjiEmpty();
//        verify(mMockMainMvpView, never()).showKanji(anyListOf(Ribot.class));
    }
}
