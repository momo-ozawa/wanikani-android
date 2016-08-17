package com.mozawa.wanikaniandroid.ui.radicals;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.Radicals;
import com.mozawa.wanikaniandroid.ui.base.BaseActivity;
import com.mozawa.wanikaniandroid.ui.base.BaseFragment;
import com.mozawa.wanikaniandroid.ui.util.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadicalsFragment extends BaseFragment implements RadicalsMvpView {

    @Inject
    RadicalsPresenter presenter;

    @Inject
    RadicalsAdapter radicalsAdapter;

    @BindView(R.id.radicalsRecyclerView)
    RecyclerView radicalsRecyclerView;

    public RadicalsFragment() {
        // Required empty public constructor
    }

    public static RadicalsFragment newInstance() {
        RadicalsFragment fragment = new RadicalsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_radicals, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);

        presenter.attachView(this);
        presenter.loadRadicals();

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
    public void showRadicals(Radicals radicals) {
        radicalsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        radicalsRecyclerView.setAdapter(radicalsAdapter);
        radicalsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext()));

        radicalsAdapter.setContext(getContext());
        radicalsAdapter.setRadicalInformationList(radicals.radicalInformationList);
        radicalsAdapter.notifyDataSetChanged();
    }
}
