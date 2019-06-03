package com.example.newsread.Presenter;

import com.example.newsread.Contracts.AddFavoratiesContract;
import com.example.newsread.model.Article;
import com.google.firebase.auth.FirebaseUser;

public class AddFavoratiesPresenter implements AddFavoratiesContract.IAddNewsFavorPresenter {

    private AddFavoratiesContract.IAdapterAddNewsFavorView adapter;
    private AddFavoratiesContract.IAddNewsFavorIntractor firebaseAddIntractor;

    public AddFavoratiesPresenter(AddFavoratiesContract.IAdapterAddNewsFavorView adapter,
                                  AddFavoratiesContract.IAddNewsFavorIntractor firebaseAddIntractor) {
        this.adapter = adapter;
        this.firebaseAddIntractor = firebaseAddIntractor;
    }

    @Override
    public void annNews(Article article, FirebaseUser firebaseUser) {
        firebaseAddIntractor.performFirebaseAddNews(article, firebaseUser);
    }
}
