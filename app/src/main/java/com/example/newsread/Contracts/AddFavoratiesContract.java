package com.example.newsread.Contracts;

import com.example.newsread.model.Article;
import com.google.firebase.auth.FirebaseUser;

public interface AddFavoratiesContract {

    interface IAdapterAddNewsFavorView
    {
    }

    interface IAddNewsFavorPresenter
    {
        void annNews(Article article, FirebaseUser firebaseUser);
    }

    interface IAddNewsFavorIntractor
    {
        void performFirebaseAddNews(Article article, FirebaseUser firebaseUser);
    }
}
