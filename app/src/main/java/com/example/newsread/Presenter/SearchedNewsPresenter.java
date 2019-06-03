package com.example.newsread.Presenter;

import com.example.newsread.Contracts.NewsContract;
import com.example.newsread.model.Article;

import java.util.ArrayList;

public class SearchedNewsPresenter extends NewsPresenter {

    public SearchedNewsPresenter(NewsContract.ShowNewsActivity showNewsActivity, NewsContract.GetNewsIntractor getNewsIntractor) {
        super(showNewsActivity, getNewsIntractor);
    }

    public void requestDataFormsServer(String tag) {
        showNewsActivity.hideListNews();
        showNewsActivity.showProgress();
        getNewsIntractor.getNewsArrayList(this, tag);
    }

    @Override
    public void onFinished(ArrayList<Article> articles) {
        if (showNewsActivity != null)
        {
            showNewsActivity.setDataTorecyclerView(articles);
            showNewsActivity.hideProgress();
            showNewsActivity.showListNews();
            showNewsActivity.setArticles(articles);
            showNewsActivity.setStringEndData(articles);
        }
    }
}
