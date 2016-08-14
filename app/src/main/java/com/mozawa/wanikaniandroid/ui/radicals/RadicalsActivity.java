package com.mozawa.wanikaniandroid.ui.radicals;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.Radicals;
import com.mozawa.wanikaniandroid.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadicalsActivity extends BaseActivity implements RadicalsMvpView {

    @Inject
    RadicalsPresenter presenter;

    @Inject
    RadicalsAdapter radicalsAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.radicalsRecyclerView)
    RecyclerView radicalsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_radicals);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter.attachView(this);
        presenter.loadRadicals();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void showRadicals(Radicals radicals) {
        radicalsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        radicalsRecyclerView.setAdapter(radicalsAdapter);

        radicalsAdapter.setRadicalInformationList(radicals.radicalInformationList);
        radicalsAdapter.notifyDataSetChanged();
    }
}
