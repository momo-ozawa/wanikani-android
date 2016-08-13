package com.mozawa.wanikaniandroid.injection.component;


import com.mozawa.wanikaniandroid.injection.PerActivity;
import com.mozawa.wanikaniandroid.injection.module.ActivityModule;
import com.mozawa.wanikaniandroid.ui.WebViewActivity;
import com.mozawa.wanikaniandroid.ui.base.BaseActivity;
import com.mozawa.wanikaniandroid.ui.main.MainActivity;
import com.mozawa.wanikaniandroid.ui.radicals.RadicalsActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);
    void inject(MainActivity mainActivity);
    void inject(WebViewActivity webViewActivity);
    void inject(RadicalsActivity radicalsActivity);

}
