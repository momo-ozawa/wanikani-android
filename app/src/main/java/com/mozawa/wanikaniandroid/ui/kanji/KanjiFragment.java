package com.mozawa.wanikaniandroid.ui.kanji;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.Kanji;
import com.mozawa.wanikaniandroid.ui.base.BaseActivity;
import com.mozawa.wanikaniandroid.ui.base.BaseFragment;
import com.mozawa.wanikaniandroid.ui.util.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KanjiFragment extends BaseFragment implements KanjiMvpView {

    @Inject
    KanjiPresenter presenter;

    @Inject
    KanjiAdapter kanjiAdapter;

    @BindView(R.id.kanjiRecyclerView)
    RecyclerView kanjiRecyclerView;

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


    @Override
    public void showKanji(List<Kanji> kanjiList) {
        kanjiRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        kanjiRecyclerView.setAdapter(kanjiAdapter);
        kanjiRecyclerView.addItemDecoration(new DividerItemDecoration(getContext()));

        kanjiAdapter.setContext(getContext());
        kanjiAdapter.setKanjiList(kanjiList);
        kanjiAdapter.notifyDataSetChanged();
    }
}
