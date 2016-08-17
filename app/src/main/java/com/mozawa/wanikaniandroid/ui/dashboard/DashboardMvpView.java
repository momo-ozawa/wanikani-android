package com.mozawa.wanikaniandroid.ui.dashboard;


import com.mozawa.wanikaniandroid.data.model.CriticalItems;
import com.mozawa.wanikaniandroid.data.model.StudyQueue;
import com.mozawa.wanikaniandroid.ui.base.MvpView;

public interface DashboardMvpView extends MvpView {

    void showAvailableStudyQueue(StudyQueue studyQueue);
    void showReviewStudyQueue(StudyQueue studyQueue);
    void showCriticalItems(CriticalItems criticalItems);

}
