package com.example.newsread.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newsread.Contracts.LoginContract;
import com.example.newsread.Presenter.LoginPresenter;
import com.example.newsread.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.ILoginView {

    private LoginPresenter loginPresenter;
    private String login;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        loginPresenter = new LoginPresenter(this);
        EditText loginEnter = (EditText) findViewById(R.id.login_login_enter);
        EditText passwordEnter = (EditText) findViewById(R.id.password_login_enter);

        findViewById(R.id.button_read_without_login).setOnClickListener((View view) ->
        {
            Intent goToMainActivity = new Intent(this, MainActivity.class);
            startActivity(goToMainActivity);
        });

        findViewById(R.id.button_registration).setOnClickListener((View view) ->
        {
            Intent goToRegistrationActivity = new Intent(this, RegistrationActivity.class);
            startActivity(goToRegistrationActivity);
        });

        findViewById(R.id.button_login_enter).setOnClickListener((View view) ->
        {
            login = loginEnter.getText().toString();
            password = passwordEnter.getText().toString();
            if (!login.isEmpty() || !password.isEmpty())
            {
                loginPresenter.login(this, login, password);
            }
            else
            {
                Toast.makeText(this, "Fields empty", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onLoginSuccess(String message) {
        Toast.makeText(getApplicationContext(), "Successfully Logged in", Toast.LENGTH_SHORT).show();
        Intent goToLoginMainActivity = new Intent(this, MainActivityForLogin.class);
        startActivity(goToLoginMainActivity);
    }

    @Override
    public void onLoginFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}