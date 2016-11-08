package com.mozawa.wanikaniandroid.ui.radicals;


import com.mozawa.wanikaniandroid.data.model.Radical;
import com.mozawa.wanikaniandroid.ui.base.MvpView;

import java.util.List;

public interface RadicalsMvpView extends MvpView {

    void showRadicals(List<Radical> radicalList);

}
