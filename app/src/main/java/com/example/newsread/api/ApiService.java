package com.example.newsread.api;

import com.example.newsread.model.ListArticles;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/v2/top-headlines?country=us&apiKey=77faaa64c6ab4155a5dd9f160f8720ca")
    Call<ListArticles> getMyJSON();
}
