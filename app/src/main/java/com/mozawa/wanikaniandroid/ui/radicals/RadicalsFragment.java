package com.mozawa.wanikaniandroid.ui.radicals;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    @BindView(R.id.radicalsMessageTextView)
    TextView radicalsMessageTextView;
    @BindView(R.id.radicalsProgressBar)
    ProgressBar radicalsProgressBar;

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

    /********
     * RadicalsMvpView implementation
     *******/

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            radicalsProgressBar.setVisibility(View.VISIBLE);
            radicalsRecyclerView.setVisibility(View.GONE);
            radicalsMessageTextView.setVisibility(View.GONE);
        } else {
            radicalsProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showRadicals(List<Radical> radicalList) {
        if (radicalsMessageTextView.getVisibility() == View.VISIBLE) {
            radicalsMessageTextView.setVisibility(View.GONE);
        }
        radicalsRecyclerView.setVisibility(View.VISIBLE);

        radicalsRecyclerView.setHasFixedSize(true);
        radicalsRecyclerView.setAdapter(radicalsAdapter);

        radicalsAdapter.setContext(getContext());
        radicalsAdapter.setRadicalList(radicalList);
        radicalsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showRadicalsEmpty() {
        showMessage("No radicals to show.");
    }

    @Override
    public void showError() {
        showMessage("There was an error loading the radicals.");
    }

    public void showMessage(String message) {
        radicalsRecyclerView.setVisibility(View.GONE);
        radicalsMessageTextView.setVisibility(View.VISIBLE);
        radicalsMessageTextView.setText(message);
    }
}
