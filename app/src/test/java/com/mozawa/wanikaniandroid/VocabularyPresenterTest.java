package com.mozawa.wanikaniandroid;

import com.mozawa.wanikaniandroid.data.DataManager;
import com.mozawa.wanikaniandroid.data.model.Vocabulary;
import com.mozawa.wanikaniandroid.ui.vocabulary.VocabularyMvpView;
import com.mozawa.wanikaniandroid.ui.vocabulary.VocabularyPresenter;
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
public class VocabularyPresenterTest {

    @Mock
    VocabularyMvpView mockVocabularyMvpView;

    @Mock
    DataManager mockDataManager;

    private VocabularyPresenter vocabularyPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        vocabularyPresenter = new VocabularyPresenter(mockDataManager);
        vocabularyPresenter.attachView(mockVocabularyMvpView);
    }

    @After
    public void tearDown() {
        vocabularyPresenter.detachView();
    }

    @Test
    public void loadVocabularyReturnsVocabulary() {
        List<Vocabulary> kanjiList = TestDataFactory.makeVocabularyList(10);
        when(mockDataManager.getVocabulary())
                .thenReturn(Observable.just(kanjiList));

        vocabularyPresenter.loadVocabulary();
        verify(mockVocabularyMvpView, times(1)).showVocabulary(kanjiList);
        verify(mockVocabularyMvpView, times(1)).showProgressBar(true);
        verify(mockVocabularyMvpView, times(1)).showProgressBar(false);
        verify(mockVocabularyMvpView, never()).showError();
        verify(mockVocabularyMvpView, never()).showVocabularyEmpty();
    }

    @Test
    public void loadVocabularyReturnsEmptyList() {
        when(mockDataManager.getVocabulary())
                .thenReturn(Observable.just(Collections.<Vocabulary>emptyList()));

        vocabularyPresenter.loadVocabulary();
        verify(mockVocabularyMvpView, times(1)).showVocabularyEmpty();
        verify(mockVocabularyMvpView, times(1)).showProgressBar(true);
        verify(mockVocabularyMvpView, times(1)).showProgressBar(false);
        verify(mockVocabularyMvpView, never()).showVocabulary(anyListOf(Vocabulary.class));
        verify(mockVocabularyMvpView, never()).showError();
    }

    @Test
    public void loadVocabularyFails() {
        when(mockDataManager.getVocabulary())
                .thenReturn(Observable.<List<Vocabulary>>error(new RuntimeException()));

        vocabularyPresenter.loadVocabulary();
        verify(mockVocabularyMvpView, times(1)).showError();
        verify(mockVocabularyMvpView, times(1)).showProgressBar(true);
        verify(mockVocabularyMvpView, times(1)).showProgressBar(false);
        verify(mockVocabularyMvpView, never()).showVocabularyEmpty();
        verify(mockVocabularyMvpView, never()).showVocabulary(anyListOf(Vocabulary.class));
    }

}