package com.example.newsread.api;

import com.example.newsread.model.ListArticles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/v2/top-headlines?country=us&apiKey=77faaa64c6ab4155a5dd9f160f8720ca")
    Call<ListArticles> getMyJSON();

    //&to=2019-05-21&
    @GET("/v2/everything?q=all&to={date}&apiKey=77faaa64c6ab4155a5dd9f160f8720ca")
    Call<ListArticles> getMyJSONToDate(@Path("date") String date);
}
