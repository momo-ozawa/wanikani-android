package com.mozawa.wanikaniandroid.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.CriticalItem;
import com.mozawa.wanikaniandroid.data.model.StudyQueue;
import com.mozawa.wanikaniandroid.ui.WebViewActivity;
import com.mozawa.wanikaniandroid.ui.base.BaseActivity;
import com.mozawa.wanikaniandroid.ui.base.BaseFragment;
import com.mozawa.wanikaniandroid.util.TimeUtil;
import com.mozawa.wanikaniandroid.ui.widgets.CircleTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardFragment extends BaseFragment implements DashboardMvpView {

    @Inject
    DashboardPresenter presenter;

    @Inject
    CriticalItemsAdapter criticalItemsAdapter;

    // Available Now
    @BindView(R.id.lessonsCircleTextView)
    CircleTextView lessonsCircleTextView;
    @BindView(R.id.lessonsButton)
    Button lessonsButton;
    @BindView(R.id.reviewsCircleTextView)
    CircleTextView reviewsCircleTextView;
    @BindView(R.id.reviewsButton)
    Button reviewsButton;

    // Coming Up
    @BindView(R.id.reviewDateTextView)
    TextView reviewDateTextView;
    @BindView(R.id.nextHourCircleTextView)
    CircleTextView nextHourCircleTextView;
    @BindView(R.id.nextDayCircleTextView)
    CircleTextView nextDayCircleTextView;

    // Critical Items
    @BindView(R.id.criticalItemsRecyclerView)
    RecyclerView criticalItemsRecyclerView;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);

        presenter.attachView(this);
        presenter.loadStudyQueue();
        presenter.loadCriticalItems();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showAvailableStudyQueue(StudyQueue studyQueue) {
        lessonsCircleTextView.setText(studyQueue.lessonsAvailable + "");
        reviewsCircleTextView.setText(studyQueue.reviewsAvailable + "");
    }

    @Override
    public void showReviewStudyQueue(StudyQueue studyQueue) {
        long nextReviewDate = studyQueue.nextReviewDate;
        reviewDateTextView.setText("Reviews available " + TimeUtil.getTimeUntil(nextReviewDate));
        nextHourCircleTextView.setText(studyQueue.reviewsAvailableNextHour + "");
        nextDayCircleTextView.setText(studyQueue.reviewsAvailableNextDay + "");
    }

    @Override
    public void showCriticalItems(List<CriticalItem> criticalItemList) {
        criticalItemsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        criticalItemsRecyclerView.setAdapter(criticalItemsAdapter);

        criticalItemsAdapter.setCriticalItemList(criticalItemList);
        criticalItemsAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.lessonsButton, R.id.reviewsButton})
    public void onClick(View view) {

        String toolbarTitle = null;
        String url = null;

        switch (view.getId()) {

            case R.id.lessonsButton:
                toolbarTitle = "Lessons";
                url = "https://www.wanikani.com/lesson/session";
                break;

            case R.id.reviewsButton:
                toolbarTitle = "Reviews";
                url = "https://www.wanikani.com/review/session";
                break;
        }

        startActivity(WebViewActivity.getStartIntent(getContext(), toolbarTitle, url));
    }
}
