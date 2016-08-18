package com.mozawa.wanikaniandroid.ui.kanji;


import com.mozawa.wanikaniandroid.data.model.Kanji;
import com.mozawa.wanikaniandroid.ui.base.MvpView;

public interface KanjiMvpView extends MvpView {

    void showKanji(Kanji kanji);

}
