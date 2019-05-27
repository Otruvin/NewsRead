package com.example.newsread.activity;

import com.example.newsread.model.Article;

import java.util.ArrayList;

public interface NewsContract {

    interface presenter
    {
        void onDestroy();

        void requestDataFormsServer();

        void requestDataFromServerToAddArticles(String endDate, String tag);
    }

    interface ShowNewsActivity
    {
        void showProgress();

        void hideProgress();

        void setDataTorecyclerView(ArrayList<Article> articles);

        void onResponseFailure(Throwable throwable);

        void setArticles(ArrayList<Article> articles);

        void addDataToListArticles(ArrayList<Article> articles);

        void setStringEndData(ArrayList<Article> articles);

        void trueShouldLoadMore();

        void hideListNews();

        void showListNews();

    }

    interface GetNewsIntractor
    {

        interface OnFinishedListener
        {
            void onFinished(ArrayList<Article> articles);
            void onFailure(Throwable throwable);
        }

        interface OnFinishAddListener
        {
            void onFinishedAdd(ArrayList<Article> articles);
            void onFailureAdd(Throwable throwable);
        }

        void getNewsArrayList(OnFinishedListener onFinishedListener, String tag);
        void getNewsArrayList(OnFinishedListener onFinishedListener);
        void getNewsArrayListToDate(OnFinishAddListener onFinishedListener, String date, String tag);
    }
}
