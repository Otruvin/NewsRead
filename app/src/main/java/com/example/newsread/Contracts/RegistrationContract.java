package com.example.newsread.Contracts;

import android.app.Activity;

import com.google.firebase.auth.FirebaseUser;

public interface RegistrationContract {

    interface IRegistrationView
    {
        void onRegistrationSuccess(FirebaseUser firebaseUser);
        void onRegistrationFailure(String message);
    }

    interface IRegistrationPresenter
    {
        void register(Activity activity, String email, String password);
    }

    interface IRegistrationInteractor
    {
        void performRegistration(Activity activity, String email, String password);
    }

    interface onRegistrationListener
    {
        void onSuccess(FirebaseUser firebaseUser);
        void onFailure(String message);
    }
}
