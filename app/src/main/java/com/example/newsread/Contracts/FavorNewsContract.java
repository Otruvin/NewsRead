package com.example.newsread.Contracts;

import com.example.newsread.Interactor.GetFavorIntractor;
import com.example.newsread.model.Article;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;

import java.util.List;

public interface FavorNewsContract {

    interface IFavorView
    {
        void initProgressBar();
        void hideProgressBar();
        void initRecyclerView();
        void setDataToRecyclerView(List<Article> articles);
        void showProgressBar();
        void onFailureLoadFavor();
    }

    interface IFavorPresenter
    {
        void requestFavorData(FirebaseUser firebaseUser);
    }

    interface IFavorIntractor
    {
        void loadFavorDataFromDatabase(FirebaseUser firebaseUser);
    }

    interface OnLoadFavorListener
    {
        void onLoadFavorFinished(List<Article> articles);
        void onLoadFavorFailure(DatabaseError error);
    }
}
