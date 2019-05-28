package com.example.newsread.activity;

import com.example.newsread.model.Article;

import java.util.ArrayList;

public class NewsPresenter implements NewsContract.presenter, NewsContract.GetNewsIntractor.OnFinishedListener,
                                        NewsContract.GetNewsIntractor.OnFinishAddListener{

    protected NewsContract.ShowNewsActivity showNewsActivity;
    protected NewsContract.GetNewsIntractor getNewsIntractor;

    public NewsPresenter(NewsContract.ShowNewsActivity showNewsActivity, NewsContract.GetNewsIntractor getNewsIntractor) {
        this.showNewsActivity = showNewsActivity;
        this.getNewsIntractor = getNewsIntractor;
    }

    @Override
    public void onDestroy() {
        showNewsActivity = null;
    }

    @Override
    public void requestDataFormsServer() {
        showNewsActivity.showProgress();
        getNewsIntractor.getNewsArrayList(this);

    }

    @Override
    public void onFinished(ArrayList<Article> articles) {
        if (showNewsActivity != null)
        {
            showNewsActivity.setDataTorecyclerView(articles);
            showNewsActivity.hideProgress();
            showNewsActivity.setArticles(articles);
            showNewsActivity.setStringEndData(articles);

        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        if (showNewsActivity != null)
        {
            showNewsActivity.onResponseFailure(throwable);
            showNewsActivity.hideProgress();
        }
    }

    @Override
    public void requestDataFromServerToAddArticles(String endDate, String tag) {
        getNewsIntractor.getNewsArrayListToDate(this, endDate, tag);
    }

    @Override
    public void onFinishedAdd(ArrayList<Article> articles) {
        if (showNewsActivity != null)
        {
            showNewsActivity.addDataToListArticles(articles);
            showNewsActivity.setStringEndData(articles);
            showNewsActivity.trueShouldLoadMore();
        }
    }

    @Override
    public void onFailureAdd(Throwable throwable) {
        if (showNewsActivity != null)
        {
            showNewsActivity.onResponseFailure(throwable);
            showNewsActivity.hideProgress();
        }
    }

}
