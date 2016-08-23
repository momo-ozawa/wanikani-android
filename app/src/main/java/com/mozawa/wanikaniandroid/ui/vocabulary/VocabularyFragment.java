package com.mozawa.wanikaniandroid.ui.vocabulary;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.Vocabulary;
import com.mozawa.wanikaniandroid.ui.base.BaseActivity;
import com.mozawa.wanikaniandroid.ui.base.BaseFragment;
import com.mozawa.wanikaniandroid.ui.util.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VocabularyFragment extends BaseFragment implements VocabularyMvpView {

    @Inject
    VocabularyPresenter presenter;

    @Inject
    VocabularyAdapter vocabularyAdapter;

    @BindView(R.id.vocabularyRecyclerView)
    RecyclerView vocabularyRecyclerView;

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

    @Override
    public void showVocabulary(Vocabulary vocabulary) {
        vocabularyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        vocabularyRecyclerView.setAdapter(vocabularyAdapter);
        vocabularyRecyclerView.addItemDecoration(new DividerItemDecoration(getContext()));

        vocabularyAdapter.setContext(getContext());
        vocabularyAdapter.setRadicalInformationList(vocabulary.vocabularyInformationList);
        vocabularyAdapter.notifyDataSetChanged();
    }
}