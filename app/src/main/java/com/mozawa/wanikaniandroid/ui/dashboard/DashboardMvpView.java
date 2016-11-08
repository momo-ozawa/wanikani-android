package com.mozawa.wanikaniandroid.ui.dashboard;


import com.mozawa.wanikaniandroid.data.model.CriticalItem;
import com.mozawa.wanikaniandroid.data.model.StudyQueue;
import com.mozawa.wanikaniandroid.ui.base.MvpView;

import java.util.List;

public interface DashboardMvpView extends MvpView {

    void showAvailableStudyQueue(StudyQueue studyQueue);
    void showReviewStudyQueue(StudyQueue studyQueue);
    void showCriticalItems(List<CriticalItem> criticalItemList);

}
