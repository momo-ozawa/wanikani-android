package com.mozawa.wanikaniandroid.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {

    public static final String EXTRA_TOOLBAR_TITLE = "WebViewActivity.EXTRA_TOOLBAR_TITLE";
    public static final String EXTRA_URL = "WebViewActivity.EXTRA_URL";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    WebView webView;

    String toolbarTitle;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);

        // fetch extras from previous activity
        getExtras();

        toolbar.setTitle(toolbarTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setCustomWebViewSettings();
        webView.loadUrl(url);

        Log.d("momo", toolbarTitle);
    }

    public static Intent getStartIntent(Context context, String toolbarTitle, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(EXTRA_TOOLBAR_TITLE, toolbarTitle);
        intent.putExtra(EXTRA_URL, url);
        return intent;
    }

    private void setCustomWebViewSettings() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() { // allows use in-situ
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            toolbarTitle = extras.getString(EXTRA_TOOLBAR_TITLE);
            url = extras.getString(EXTRA_URL);
        }
    }
}
