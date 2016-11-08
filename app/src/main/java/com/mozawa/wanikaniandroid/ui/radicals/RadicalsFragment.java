package com.mozawa.wanikaniandroid.ui.radicals;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.Radical;
import com.mozawa.wanikaniandroid.ui.base.BaseActivity;
import com.mozawa.wanikaniandroid.ui.base.BaseFragment;
import com.mozawa.wanikaniandroid.ui.widgets.AutofitRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadicalsFragment extends BaseFragment implements RadicalsMvpView {

    @Inject
    RadicalsPresenter presenter;

    @Inject
    RadicalsAdapter radicalsAdapter;

    @BindView(R.id.radicalsRecyclerView)
    AutofitRecyclerView radicalsRecyclerView;

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
    public void showRadicals(List<Radical> radicalList) {
        radicalsRecyclerView.setHasFixedSize(true);
        radicalsRecyclerView.setAdapter(radicalsAdapter);

        radicalsAdapter.setContext(getContext());
        radicalsAdapter.setRadicalList(radicalList);
        radicalsAdapter.notifyDataSetChanged();
    }
}
