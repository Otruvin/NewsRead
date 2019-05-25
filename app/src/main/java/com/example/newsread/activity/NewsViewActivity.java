package com.example.newsread.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.newsread.R;

import retrofit2.http.Url;

public class NewsViewActivity extends AppCompatActivity{

    WebView viewNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_view);
        getSupportActionBar().hide();

        viewNews = (WebView) findViewById(R.id.news_view_web);
        viewNews.setWebViewClient(new WebViewClient());
        viewNews.getSettings().getJavaScriptEnabled();
        viewNews.loadUrl(getIntent().getStringExtra("URL"));
    }

}
