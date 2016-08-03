package com.mozawa.wanikaniandroid.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    WebView webView;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        // fetch url from previous activity
        fetchUrl();
        setCustomWebViewSettings();
        webView.loadUrl(url);
    }

    private void setCustomWebViewSettings() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() { // allows use in-situ
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("URL", url);
                view.loadUrl(url);
                return false;
            }
        });
    }

    private void fetchUrl() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            url = extras.getString("URL");
        }
    }
}
