package com.mozawa.wanikaniandroid.ui.kanji;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.Kanji;
import com.mozawa.wanikaniandroid.data.model.ListItem;
import com.mozawa.wanikaniandroid.ui.base.BaseActivity;
import com.mozawa.wanikaniandroid.ui.base.BaseFragment;
import com.mozawa.wanikaniandroid.ui.kanjidetail.KanjiDetailActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KanjiFragment extends BaseFragment implements KanjiMvpView,
        KanjiAdapter.KanjiClickedListener {

    @Inject
    KanjiPresenter presenter;

    @Inject
    KanjiAdapter kanjiAdapter;

    @BindView(R.id.kanjiRecyclerView)
    RecyclerView kanjiRecyclerView;
    @BindView(R.id.kanjiMessageTextView)
    TextView kanjiMessageTextView;
    @BindView(R.id.kanjiProgressBar)
    ProgressBar kanjiProgressBar;

    public KanjiFragment() {
        // Required empty public constructor
    }

    public static KanjiFragment newInstance() {
        KanjiFragment fragment = new KanjiFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_kanji, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);

        presenter.attachView(this);
        presenter.loadKanji();

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

    /********
     * KanjiMvpView implementation
     *******/

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            kanjiProgressBar.setVisibility(View.VISIBLE);
            kanjiRecyclerView.setVisibility(View.GONE);
            kanjiMessageTextView.setVisibility(View.GONE);
        } else {
            kanjiProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showListItems(List<ListItem> listItems) {
        if (kanjiMessageTextView.getVisibility() == View.VISIBLE) {
            kanjiMessageTextView.setVisibility(View.GONE);
        }
        kanjiRecyclerView.setVisibility(View.VISIBLE);

        kanjiRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        kanjiRecyclerView.setAdapter(kanjiAdapter);

        kanjiAdapter.setContext(getContext());
        kanjiAdapter.setListItems(listItems);
        kanjiAdapter.setKanjiClickedListener(this);
        kanjiAdapter.notifyDataSetChanged();
    }

    @Override
    public void showListItemsEmpty() {
        showMessage("No kanji to show.");
    }

    @Override
    public void showError() {
        showMessage("There was an error loading the kanji.");
    }

    public void showMessage(String message) {
        kanjiRecyclerView.setVisibility(View.GONE);
        kanjiMessageTextView.setVisibility(View.VISIBLE);
        kanjiMessageTextView.setText(message);
    }

    /********
     * KanjiAdapter.KanjiClickedListener implementation
     *******/

    @Override
    public void onKanjiClicked(Kanji kanji) {
        Intent intent = new Intent(getActivity(), KanjiDetailActivity.class);
        startActivity(intent);
    }
}
