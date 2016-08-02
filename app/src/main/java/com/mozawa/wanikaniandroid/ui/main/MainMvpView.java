package com.mozawa.wanikaniandroid.ui.main;

import com.mozawa.wanikaniandroid.data.model.StudyQueue;
import com.mozawa.wanikaniandroid.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showStudyQueue(StudyQueue studyQueue);

}
