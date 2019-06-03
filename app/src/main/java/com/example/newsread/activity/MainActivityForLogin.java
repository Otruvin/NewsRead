package com.example.newsread.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.newsread.Interactor.GetNewsIntractor;
import com.example.newsread.Interactor.GetNewsLocalBaseIntractor;
import com.example.newsread.Presenter.NewsPresenter;
import com.example.newsread.R;
import com.example.newsread.model.Article;
import com.example.newsread.model.NewsLoginAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;
import java.util.HashSet;

public class MainActivityForLogin extends MainActivity {

    FirebaseUser firebaseUser;
    NewsLoginAdapter newsLoginAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_login);
        initializeRecycleView(R.id.list_news_searched_login);
        initProgressBar();
        latestNews = new HashSet<String>();
        tempLatestNews = new HashSet<Article>();
        tag = "latest";

        presenter = new NewsPresenter(this, new GetNewsIntractor(), new GetNewsLocalBaseIntractor());
        presenter.getContext(this.getApplicationContext());
        presenter.requestDataFormsServer();
        intentWithNewsView = new Intent(this, NewsViewActivity.class);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        findViewById(R.id.floatingBarToSearch).setOnClickListener( (View v) ->
        {
            Intent intentToSearch = new Intent(this, SearchedNewsActivityLogin.class);
            startActivity(intentToSearch);
        });

        findViewById(R.id.floatingActionFavor).setOnClickListener((View v) ->
        {
            Intent intentToFavor = new Intent(this, FavoriteNewsMainActivity.class);
            startActivity(intentToFavor);
        });
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

    @Override
    public void notifyDataChangedInList() {
        this.newsLoginAdapter.notifyDataSetChanged();
    }
}
