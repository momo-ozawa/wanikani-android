package com.mozawa.wanikaniandroid.ui.kanji;


import com.mozawa.wanikaniandroid.data.model.ListItem;
import com.mozawa.wanikaniandroid.ui.base.MvpView;

import java.util.List;

public interface KanjiMvpView extends MvpView {

//    void showKanji(List<Kanji> kanjiList);
    void showKanjiEmpty();
    void showError();
    void showProgressBar(boolean show);

    void showListItems(List<ListItem> listItems);
}
