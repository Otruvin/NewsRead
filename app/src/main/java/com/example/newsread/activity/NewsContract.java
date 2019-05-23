package com.example.newsread.activity;

import com.example.newsread.model.Article;

import java.util.ArrayList;

public interface NewsContract {

    interface presenter
    {
        void onDestroy();

        void requestDataFormsServer();
    }

    interface ShowNewsActivity
    {
        void showProgress();

        void hideProgress();

        void setDataTorecyclerView(ArrayList<Article> articles);

        void onResponseFailure(Throwable throwable);
    }

    interface GetNewsIntractor
    {

        interface OnFinishedListener
        {
            void onFinished(ArrayList<Article> articles);
            void onFailure(Throwable throwable);
        }

        void getNewsArrayList(OnFinishedListener onFinishedListener);
    }
}
