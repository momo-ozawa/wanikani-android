package com.mozawa.wanikaniandroid.ui.vocabulary;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.Vocabulary;
import com.mozawa.wanikaniandroid.ui.base.BaseActivity;
import com.mozawa.wanikaniandroid.ui.base.BaseFragment;
import com.mozawa.wanikaniandroid.ui.widgets.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VocabularyFragment extends BaseFragment implements VocabularyMvpView {

    @Inject
    VocabularyPresenter presenter;

    @Inject
    VocabularyAdapter vocabularyAdapter;

    @BindView(R.id.vocabRecyclerView)
    RecyclerView vocabRecyclerView;
    @BindView(R.id.vocabMessageTextView)
    TextView vocabMessageTextView;
    @BindView(R.id.vocabProgressBar)
    ProgressBar vocabProgressBar;


    public VocabularyFragment() {
        // Required empty public constructor
    }

    public static VocabularyFragment newInstance() {
        VocabularyFragment fragment = new VocabularyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_vocabulary, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);

        presenter.attachView(this);
        presenter.loadVocabulary();

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
     *  VocabularyMvpView implementation
     *******/

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            vocabProgressBar.setVisibility(View.VISIBLE);
            vocabRecyclerView.setVisibility(View.GONE);
            vocabMessageTextView.setVisibility(View.GONE);
        } else {
            vocabProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showVocabulary(List<Vocabulary> vocabularyList) {
        if (vocabMessageTextView.getVisibility() == View.VISIBLE) {
            vocabMessageTextView.setVisibility(View.GONE);
        }
        vocabRecyclerView.setVisibility(View.VISIBLE);

        vocabRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        vocabRecyclerView.setAdapter(vocabularyAdapter);
        vocabRecyclerView.addItemDecoration(new DividerItemDecoration(getContext()));

        vocabularyAdapter.setContext(getContext());
        vocabularyAdapter.setVocabularyList(vocabularyList);
        vocabularyAdapter.notifyDataSetChanged();
    }

    @Override
    public void showVocabularyEmpty() {
        showMessage("No vocabulary to show.");
    }

    @Override
    public void showError() {
        showMessage("There was an error loading the vocabulary list.");
    }

    public void showMessage(String message) {
        vocabRecyclerView.setVisibility(View.GONE);
        vocabMessageTextView.setVisibility(View.VISIBLE);
        vocabMessageTextView.setText(message);
    }
}
