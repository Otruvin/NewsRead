package com.example.newsread.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.newsread.api.ApiService;
import com.example.newsread.R;
import com.example.newsread.api.RetroClient;
import com.example.newsread.model.Article;
import com.example.newsread.model.ListArticles;
import com.example.newsread.model.PieceNewsAdapter;
import com.example.newsread.model.RecyclerClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsContract.ShowNewsActivity {

    private NewsContract.presenter presenter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initializeRecycleView();
        initProgressBar();

        presenter = new NewsPresenter(this, new GetNewsIntractor());
        presenter.requestDataFormsServer();



    }

    private RecyclerClickListener recyclerClickListener = new RecyclerClickListener() {
        @Override
        public void onItemClick(Article article) {
            Toast.makeText(MainActivity.this, "List title: " +
                    article.getTitle(), Toast.LENGTH_SHORT).show();
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
        PieceNewsAdapter pieceNewsAdapter = new PieceNewsAdapter(articles, recyclerClickListener, MainActivity.this);
        recyclerView.setAdapter(pieceNewsAdapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(this, "Error with download", Toast.LENGTH_SHORT).show();
        Log.d("Fail", "We suck in view");
    }

    private void initProgressBar()
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

    private void initializeRecycleView()
    {
        recyclerView = (RecyclerView) findViewById(R.id.list_news);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
    }
}
