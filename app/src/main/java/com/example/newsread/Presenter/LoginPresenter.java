package com.example.newsread.Presenter;

import android.app.Activity;

import com.example.newsread.Contracts.LoginContract;
import com.example.newsread.Interactor.LoginInteractor;

public class LoginPresenter implements LoginContract.ILoginPresenter, LoginContract.onLoginListener {

    private LoginContract.ILoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenter(LoginContract.ILoginView loginView)
    {
        this.loginView = loginView;
        loginInteractor = new LoginInteractor(this);
    }

    @Override
    public void login(Activity activity, String email, String password) {
        loginInteractor.performLogin(activity, email, password);
    }

    @Override
    public void onSuccess(String message) {
        loginView.onLoginSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        loginView.onLoginFailure(message);
    }
}
