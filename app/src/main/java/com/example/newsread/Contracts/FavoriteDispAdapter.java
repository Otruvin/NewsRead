package com.example.newsread.Contracts;

import com.example.newsread.model.NewsFavoriteAdapter;
import com.google.firebase.auth.FirebaseUser;

public interface FavoriteDispAdapter {

    interface IFavorAdapterView
    {
    }

    interface IFavorAdapterPresenter
    {
        void deleteFavorNews(String title, FirebaseUser firebaseUser);
    }

    interface IFavorAdapterIntractor
    {
        void performDeleteFavorNews(String title, FirebaseUser firebaseUser);
    }
}
