package com.example.newsread.Presenter;

import android.app.Activity;

import com.example.newsread.Contracts.RegistrationContract;
import com.example.newsread.Interactor.RegistrationInteractor;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationPresenter implements RegistrationContract.IRegistrationPresenter, RegistrationContract.onRegistrationListener {

    private RegistrationContract.IRegistrationView registrationView;
    private RegistrationInteractor registrationInteractor;

    public RegistrationPresenter(RegistrationContract.IRegistrationView registrationView)
    {
        this.registrationView = registrationView;
        registrationInteractor = new RegistrationInteractor(this);
    }

    @Override
    public void register(Activity activity, String email, String password) {
        registrationInteractor.performRegistration(activity, email, password);
    }

    @Override
    public void onSuccess(FirebaseUser firebaseUser) {
        registrationView.onRegistrationSuccess(firebaseUser);
    }

    @Override
    public void onFailure(String message) {
        registrationView.onRegistrationFailure(message);
    }
}
