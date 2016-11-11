package com.mozawa.wanikaniandroid.util;

import android.view.View;

public class ViewUtil {

    public static void setVisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
