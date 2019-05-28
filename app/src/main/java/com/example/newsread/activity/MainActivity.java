package com.example.newsread.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
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

import com.example.newsread.R;
import com.example.newsread.model.Article;
import com.example.newsread.model.PieceNewsAdapter;
import com.example.newsread.model.RecyclerClickListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements NewsContract.ShowNewsActivity {

    protected NewsContract.presenter presenter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private ArrayList<Article> articles;
    private RecyclerView.LayoutManager layoutManager;
    protected Intent intentWithNewsView;
    private PieceNewsAdapter pieceNewsAdapter;
    private boolean itShouldLoadMore = true;
    private String endData;
    protected Set<String> latestNews;
    protected Set<Article> tempLatestNews;
    protected String tag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initializeRecycleView(R.id.list_news_searched);
        initProgressBar();
        latestNews = new HashSet<String>();
        tempLatestNews = new HashSet<Article>();
        tag = "latest";

        presenter = new NewsPresenter(this, new GetNewsIntractor());
        presenter.requestDataFormsServer();
        intentWithNewsView = new Intent(this, NewsViewActivity.class);

        findViewById(R.id.floatingBarToSearch).setOnClickListener( (View v) ->
        {
            Intent intentToSearch = new Intent(this, SearchedNewsActivity.class);
            startActivity(intentToSearch);
        });

    }

    private RecyclerClickListener recyclerClickListener = new RecyclerClickListener() {
        @Override
        public void onItemClick(Article article) {
            intentWithNewsView.putExtra("URL", article.getUrl());
            Log.wtf("Title: ", article.getTitle());
            startActivity(intentWithNewsView);
        }
    };

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataTorecyclerView(ArrayList<Article> articles) {
        pieceNewsAdapter = new PieceNewsAdapter(articles, recyclerClickListener, recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(pieceNewsAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                if (dy > 0)
                {
                    if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN))
                    {
                        if (itShouldLoadMore)
                        {
                            presenter.requestDataFromServerToAddArticles(endData, tag);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(this, "Error with download", Toast.LENGTH_SHORT).show();
        Log.d("Fail", "We suck in view");
    }

    protected void initProgressBar()
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

    protected void initializeRecycleView(int idRecView)
    {
        recyclerView = (RecyclerView) findViewById(idRecView);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            layoutManager = new GridLayoutManager(MainActivity.this, 2);
        }
        else
        {
            layoutManager = new GridLayoutManager(MainActivity.this, 1);
        }

        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    @Override
    public void addDataToListArticles(ArrayList<Article> articles) {

        for (int i = 0; i < articles.size(); ++i)
        {
            if (!latestNews.contains(articles.get(i).getTitle()))
            {
                latestNews.add(articles.get(i).getTitle());
                tempLatestNews.add(articles.get(i));
            }
        }

        itShouldLoadMore = false;
        this.articles.addAll(tempLatestNews);
        this.pieceNewsAdapter.notifyDataSetChanged();
        tempLatestNews.clear();

    }

    @Override
    public void setStringEndData(ArrayList<Article> articles) {
        this.endData = articles.get(articles.size() - 1).getPublishedAt();
    }

    @Override
    public void trueShouldLoadMore() {
        this.itShouldLoadMore = true;
    }

    @Override
    public void hideListNews() {
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showListNews() {
        recyclerView.setVisibility(View.VISIBLE);
    }
}
