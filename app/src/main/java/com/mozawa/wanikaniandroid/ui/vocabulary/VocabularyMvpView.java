package com.mozawa.wanikaniandroid.ui.vocabulary;


import com.mozawa.wanikaniandroid.data.model.Vocabulary;
import com.mozawa.wanikaniandroid.ui.base.MvpView;

import java.util.List;

public interface VocabularyMvpView extends MvpView {

    void showProgressBar(boolean show);
    void showVocabulary(List<Vocabulary> vocabularyList);
    void showVocabularyEmpty();
    void showError();

}
