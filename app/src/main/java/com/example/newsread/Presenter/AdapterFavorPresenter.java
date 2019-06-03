package com.example.newsread.Presenter;

import com.example.newsread.Contracts.FavoriteDispAdapter;
import com.example.newsread.Interactor.AdapterFavorIntractor;
import com.example.newsread.model.NewsFavoriteAdapter;
import com.google.firebase.auth.FirebaseUser;

public class AdapterFavorPresenter implements FavoriteDispAdapter.IFavorAdapterPresenter {


    private AdapterFavorIntractor intractor;

    public AdapterFavorPresenter() {
        this.intractor = new AdapterFavorIntractor();
    }

    @Override
    public void deleteFavorNews(String title, FirebaseUser firebaseUser) {
        intractor.performDeleteFavorNews(title, firebaseUser);
    }
}
