package com.example.newsread.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.newsread.Contracts.FavorNewsContract;
import com.example.newsread.Interactor.GetFavorIntractor;
import com.example.newsread.Presenter.FavoriteNewsPresenter;
import com.example.newsread.R;
import com.example.newsread.model.Article;
import com.example.newsread.model.NewsFavoriteAdapter;
import com.example.newsread.model.RecyclerClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class FavoriteNewsMainActivity extends AppCompatActivity implements FavorNewsContract.IFavorView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FavoriteNewsPresenter presenter;
    private NewsFavoriteAdapter favoriteAdapter;
    private FirebaseUser loginUser;
    private Intent intentWithNewsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_news_main);
        getSupportActionBar().hide();
        initProgressBar();
        loginUser = FirebaseAuth.getInstance().getCurrentUser();
        intentWithNewsView = new Intent(this, NewsViewActivity.class);
        presenter = new FavoriteNewsPresenter(this);
        presenter.requestFavorData(loginUser);
    }

    protected RecyclerClickListener recyclerClickListener = new RecyclerClickListener() {
        @Override
        public void onItemClick(Article article) {
            intentWithNewsView.putExtra("URL", article.getUrl());
            Log.wtf("Title: ", article.getTitle());
            startActivity(intentWithNewsView);
        }
    };

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailureLoadFavor() {
        Toast.makeText(this, "Error with load", Toast.LENGTH_SHORT);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.list_favor_rec_view);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            layoutManager = new GridLayoutManager(FavoriteNewsMainActivity.this, 2);
        }
        else
        {
            layoutManager = new GridLayoutManager(FavoriteNewsMainActivity.this, 1);
        }

        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void setDataToRecyclerView(List<Article> articles) {
        favoriteAdapter = new NewsFavoriteAdapter(loginUser, articles, recyclerClickListener);
        recyclerView.setHasFixedSize(true);
        this.runOnUiThread(() ->
        {
            recyclerView.setAdapter(favoriteAdapter);
        });
        recyclerView.setAdapter(favoriteAdapter);
    }

    @Override
    public void initProgressBar()
    {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }
}
