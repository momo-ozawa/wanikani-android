package com.mozawa.wanikaniandroid.injection.component;


import com.mozawa.wanikaniandroid.injection.PerActivity;
import com.mozawa.wanikaniandroid.injection.module.ActivityModule;
import com.mozawa.wanikaniandroid.ui.WebViewActivity;
import com.mozawa.wanikaniandroid.ui.base.BaseActivity;
import com.mozawa.wanikaniandroid.ui.base.BaseFragment;
import com.mozawa.wanikaniandroid.ui.dashboard.DashboardFragment;
import com.mozawa.wanikaniandroid.ui.main.MainActivity;
import com.mozawa.wanikaniandroid.ui.radicals.RadicalsFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);
    void inject(BaseFragment baseFragment);

    void inject(MainActivity mainActivity);
    void inject(DashboardFragment dashboardFragment);
    void inject(RadicalsFragment radicalsFragment);

    void inject(WebViewActivity webViewActivity);
}
