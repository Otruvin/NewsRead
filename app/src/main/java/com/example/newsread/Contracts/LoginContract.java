package com.example.newsread.Contracts;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

public interface LoginContract {

    interface ILoginView
    {
        void onLoginSuccess(String message);
        void onLoginFailure(String message);
    }

    interface ILoginPresenter
    {
        void login(Activity activity, String email, String password);
    }

    interface ILoginInteractor
    {
        void performLogin(Activity activity, String email, String password);
    }

    interface onLoginListener
    {
        void onSuccess(String message);
        void onFailure(String message);
    }
}
