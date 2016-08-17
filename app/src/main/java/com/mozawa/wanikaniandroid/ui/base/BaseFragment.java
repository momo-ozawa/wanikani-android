package com.mozawa.wanikaniandroid.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import butterknife.Unbinder;

public class BaseFragment extends Fragment {

    protected Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }
}
