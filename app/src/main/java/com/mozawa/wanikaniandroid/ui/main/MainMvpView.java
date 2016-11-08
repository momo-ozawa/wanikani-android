package com.mozawa.wanikaniandroid.ui.main;

import com.mozawa.wanikaniandroid.data.model.CriticalItemResponse;
import com.mozawa.wanikaniandroid.data.model.StudyQueueResponse;
import com.mozawa.wanikaniandroid.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showAvailableStudyQueue(StudyQueueResponse studyQueueResponse);
    void showReviewStudyQueue(StudyQueueResponse studyQueueResponse);
    void showCriticalItems(CriticalItemResponse criticalItemResponse);

}
