package com.example.newsread.Interactor;

import android.content.Context;

import com.example.newsread.Contracts.NewsContract;
import com.example.newsread.DAO.AppDatabase;
import com.example.newsread.model.Article;

import java.util.ArrayList;
import java.util.List;

public class GetNewsLocalBaseIntractor implements NewsContract.GetNewsLocalBaseIntractor {

    private AppDatabase database;

    @Override
    public void getNewsArrayList(OnFinishedGetListener onFinishedGetListener, Context context) {
        new Thread(() ->
        {
            database = AppDatabase.getInstance(context);
            onFinishedGetListener.onFinishedGet((ArrayList<Article>) database.articleDAO().getAllArticles());
        }).start();

    }

    @Override
    public void updateNewsArrayList(Context context, List<Article> articles) {
        new Thread(() ->
        {
            database = AppDatabase.getInstance(context);
            if (database.articleDAO().getCount() > 0)
            {
                database.articleDAO().updateArticles(articles);
            }
            else
            {
                database.articleDAO().insertArticles(articles);
            }

        }).start();

    }

}
