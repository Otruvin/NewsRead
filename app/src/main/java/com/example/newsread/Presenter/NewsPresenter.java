package com.example.newsread.Presenter;

import android.content.Context;

import com.example.newsread.Contracts.NewsContract;
import com.example.newsread.model.Article;
import com.example.newsread.utils.InternetConnection;

import java.util.ArrayList;

public class NewsPresenter implements NewsContract.presenter, NewsContract.GetNewsIntractor.OnFinishedListener,
                                        NewsContract.GetNewsIntractor.OnFinishAddListener,
                                        NewsContract.GetNewsLocalBaseIntractor.OnFinishedGetListener{

    protected NewsContract.ShowNewsActivity showNewsActivity;
    protected NewsContract.GetNewsIntractor getNewsIntractor;
    private NewsContract.GetNewsLocalBaseIntractor getNewsLocalBaseIntractor;
    private Context contextShowActivity;

    public NewsPresenter(NewsContract.ShowNewsActivity showNewsActivity, NewsContract.GetNewsIntractor getNewsIntractor) {
        this.showNewsActivity = showNewsActivity;
        this.getNewsIntractor = getNewsIntractor;
    }

    public NewsPresenter(NewsContract.ShowNewsActivity showNewsActivity,
                         NewsContract.GetNewsIntractor getNewsIntractor,
                         NewsContract.GetNewsLocalBaseIntractor getNewsLocalBaseIntractor) {
        this.showNewsActivity = showNewsActivity;
        this.getNewsIntractor = getNewsIntractor;
        this.getNewsLocalBaseIntractor = getNewsLocalBaseIntractor;
    }

    @Override
    public void onDestroy() {
        showNewsActivity = null;
    }

    @Override
    public void requestDataFormsServer() {

        if (InternetConnection.checkConnection(contextShowActivity))
        {
            showNewsActivity.showProgress();
            getNewsIntractor.getNewsArrayList(this);
        }
        else
        {
            getNewsLocalBaseIntractor.getNewsArrayList(this, contextShowActivity);
            showNewsActivity.showNoInternetConnectionException();
        }


    }

    @Override
    public void onFinished(ArrayList<Article> articles) {
        if (showNewsActivity != null)
        {
            showNewsActivity.setDataTorecyclerView(articles);
            showNewsActivity.hideProgress();
            getNewsLocalBaseIntractor.updateNewsArrayList(contextShowActivity, articles);
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
        if (InternetConnection.checkConnection(contextShowActivity))
        {
            getNewsIntractor.getNewsArrayListToDate(this, endDate, tag);
        }
        else
        {
            showNewsActivity.showNoInternetConnectionException();
        }
    }

    @Override
    public void onFinishedAdd(ArrayList<Article> articles) {
        if (showNewsActivity != null && InternetConnection.checkConnection(contextShowActivity))
        {
            showNewsActivity.addDataToListArticles(articles);
            showNewsActivity.setStringEndData(articles);
            showNewsActivity.trueShouldLoadMore();
            showNewsActivity.notifyDataChangedInList();
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

    @Override
    public void getContext(Context context) {
        this.contextShowActivity = context;
    }

    @Override
    public void onFinishedGet(ArrayList<Article> articles) {
        if (showNewsActivity != null)
        {
            showNewsActivity.setDataTorecyclerView(articles);
            showNewsActivity.hideProgress();
        }
    }

    @Override
    public void onFailureGet() {
        showNewsActivity.showEmptyLocalBaseException();
    }
}
