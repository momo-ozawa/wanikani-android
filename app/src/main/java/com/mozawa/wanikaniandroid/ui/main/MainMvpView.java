package com.mozawa.wanikaniandroid.ui.main;

import com.mozawa.wanikaniandroid.data.model.CriticalItems;
import com.mozawa.wanikaniandroid.data.model.StudyQueue;
import com.mozawa.wanikaniandroid.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showAvailableStudyQueue(StudyQueue studyQueue);
    void showReviewStudyQueue(StudyQueue studyQueue);
    void showCriticalItems(CriticalItems criticalItems);

}
