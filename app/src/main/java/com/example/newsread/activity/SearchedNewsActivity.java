package com.example.newsread.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newsread.Interactor.GetNewsIntractor;
import com.example.newsread.Presenter.SearchedNewsPresenter;
import com.example.newsread.R;
import com.example.newsread.model.Article;

import java.util.HashSet;

public class SearchedNewsActivity extends MainActivity {


    private EditText searchQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_news);
        initializeRecycleView(R.id.list_news_searched_llogin);
        initProgressBar();
        initSearchElem(R.id.search_query);

    }

    protected void initSearchElem(int idSearchQuery)
    {
        searchQuery = (EditText) findViewById(idSearchQuery);
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
