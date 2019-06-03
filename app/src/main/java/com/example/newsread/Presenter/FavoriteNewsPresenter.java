package com.example.newsread.Presenter;

import com.example.newsread.Contracts.FavorNewsContract;
import com.example.newsread.Contracts.FavoriteDispAdapter;
import com.example.newsread.Interactor.GetFavorIntractor;
import com.example.newsread.Interactor.LoginInteractor;
import com.example.newsread.activity.FavoriteNewsMainActivity;
import com.example.newsread.model.Article;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;

import java.util.List;

public class FavoriteNewsPresenter implements FavorNewsContract.IFavorPresenter, FavorNewsContract.OnLoadFavorListener {

    private GetFavorIntractor getFavorIntractor;
    private FavoriteNewsMainActivity showFavorActivity;

    public FavoriteNewsPresenter(FavoriteNewsMainActivity showFavorActivity) {
        this.getFavorIntractor = new GetFavorIntractor(this);
        this.showFavorActivity = showFavorActivity;
    }

    @Override
    public void requestFavorData(FirebaseUser firebaseUser) {
        if (showFavorActivity != null)
        {
            getFavorIntractor.loadFavorDataFromDatabase(firebaseUser);
            showFavorActivity.initRecyclerView();
        }
    }

    @Override
    public void onLoadFavorFinished(List<Article> articles) {
        if (showFavorActivity != null)
        {
            showFavorActivity.setDataToRecyclerView(articles);
            showFavorActivity.hideProgressBar();
        }
    }

    @Override
    public void onLoadFavorFailure(DatabaseError error) {
        if (showFavorActivity != null)
        {
            showFavorActivity.onFailureLoadFavor();
        }
    }
}
