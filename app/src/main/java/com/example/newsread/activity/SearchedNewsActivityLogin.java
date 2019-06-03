package com.example.newsread.activity;

import android.os.Bundle;

import com.example.newsread.R;
import com.example.newsread.model.Article;
import com.example.newsread.model.NewsLoginAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class SearchedNewsActivityLogin extends SearchedNewsActivity {

    private NewsLoginAdapter newsLoginAdapter;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        setContentView(R.layout.activity_searched_news_login);
        initializeRecycleView(R.id.list_news_searched_llogin);
        initProgressBar();
        initSearchElem(R.id.search_query_llogin);
    }

    @Override
    public void setDataTorecyclerView(ArrayList<Article> articles) {
        newsLoginAdapter = new NewsLoginAdapter(articles, recyclerClickListener, firebaseUser);
        recyclerView.setHasFixedSize(true);
        this.runOnUiThread(() ->
        {
            recyclerView.setAdapter(newsLoginAdapter);
        });
        recyclerView.setAdapter(newsLoginAdapter);

        addOnScrollListener();
    }
}
