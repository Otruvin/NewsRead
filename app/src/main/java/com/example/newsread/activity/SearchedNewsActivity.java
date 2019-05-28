package com.example.newsread.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsread.R;
import com.example.newsread.model.Article;

import java.util.HashSet;

public class SearchedNewsActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_news);
        initializeRecycleView(R.id.list_news_searched);
        initProgressBar();

        EditText searchQuery = (EditText) findViewById(R.id.search_query);
        searchQuery.requestFocus();
        searchQuery.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    latestNews = new HashSet<String>();
                    tempLatestNews = new HashSet<Article>();
                    tag = searchQuery.getText().toString();

                    presenter = new SearchedNewsPresenter(SearchedNewsActivity.this, new GetNewsIntractor());
                    ((SearchedNewsPresenter) presenter).requestDataFormsServer(tag);
                    return true;
                }
                return false;
            }
        });
    }

}
