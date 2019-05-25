package com.example.newsread.activity;

import android.util.Log;

import com.example.newsread.api.ApiService;
import com.example.newsread.api.RetroClient;
import com.example.newsread.model.Article;
import com.example.newsread.model.ListArticles;
import com.example.newsread.utils.InternetConnection;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNewsIntractor implements NewsContract.GetNewsIntractor {

    ApiService api = RetroClient.getApiService();

    @Override
    public void getNewsArrayList(OnFinishedListener onFinishedListener) {

        Call<ListArticles> articlesCall = api.getMyJSON();

        //Log.wtf("URL loaded", articlesCall.request().url() + "");

        articlesCall.enqueue(new Callback<ListArticles>() {
            @Override
            public void onResponse(Call<ListArticles> call, Response<ListArticles> response) {
                onFinishedListener.onFinished((ArrayList<Article>) response.body().getArticles());
            }

            @Override
            public void onFailure(Call<ListArticles> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

    @Override
    public void getNewsArrayListToDate(OnFinishedListener onFinishedListener, String date) {

        Call<ListArticles> articlesCallToDate = api.getMyJSONToDate(date);

        articlesCallToDate.enqueue(new Callback<ListArticles>() {
            @Override
            public void onResponse(Call<ListArticles> call, Response<ListArticles> response) {
                onFinishedListener.onFinished((ArrayList<Article>) response.body().getArticles());
            }

            @Override
            public void onFailure(Call<ListArticles> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
