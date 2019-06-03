package com.example.newsread.Interactor;

import android.support.annotation.NonNull;

import com.example.newsread.Contracts.NewsContract;
import com.example.newsread.api.ApiService;
import com.example.newsread.api.RetroClient;
import com.example.newsread.model.Article;
import com.example.newsread.model.ListArticles;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNewsIntractor implements NewsContract.GetNewsIntractor {

    ApiService api = RetroClient.getApiService();

    @Override
    public void getNewsArrayList(OnFinishedListener onFinishedListener) {

        Call<ListArticles> articlesCall = api.getMyJSON();


        articlesCall.enqueue(new Callback<ListArticles>() {
            @Override
            public void onResponse(@NonNull Call<ListArticles> call, @NonNull Response<ListArticles> response) {
                onFinishedListener.onFinished((ArrayList<Article>) response.body().getArticles());
            }

            @Override
            public void onFailure(@NonNull Call<ListArticles> call, @NonNull Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

    @Override
    public void getNewsArrayList(OnFinishedListener onFinishedListener, String tag) {
        Call<ListArticles> articlesCall = api.getMyJSON(tag);

        articlesCall.enqueue(new Callback<ListArticles>() {
            @Override
            public void onResponse(@NonNull Call<ListArticles> call, @NonNull Response<ListArticles> response) {
                onFinishedListener.onFinished((ArrayList<Article>) response.body().getArticles());
            }

            @Override
            public void onFailure(@NonNull Call<ListArticles> call, @NonNull Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

    @Override
    public void getNewsArrayListToDate(OnFinishAddListener onFinishedListener, String date, String tag) {

        Call<ListArticles> articlesCallToDate = api.getMyJSONToDate(date, tag);

        articlesCallToDate.enqueue(new Callback<ListArticles>() {
            @Override
            public void onResponse(@NonNull Call<ListArticles> call, @NonNull Response<ListArticles> response) {
                onFinishedListener.onFinishedAdd((ArrayList<Article>) response.body().getArticles());
            }

            @Override
            public void onFailure(@NonNull Call<ListArticles> call, @NonNull Throwable t) {
                onFinishedListener.onFailureAdd(t);
            }
        });
    }


}
