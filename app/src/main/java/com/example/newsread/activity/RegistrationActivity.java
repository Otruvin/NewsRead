package com.example.newsread.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newsread.Contracts.RegistrationContract;
import com.example.newsread.Presenter.RegistrationPresenter;
import com.example.newsread.R;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity implements RegistrationContract.IRegistrationView {

    private RegistrationPresenter registrationPresenter;
    private String login;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().hide();

        registrationPresenter = new RegistrationPresenter(this);
        EditText loginEnter = (EditText) findViewById(R.id.login_registration_enter);
        EditText passwordEnter = (EditText) findViewById(R.id.password_registration_enter);

        findViewById(R.id.button_to_register_complite).setOnClickListener((View view) ->
        {
            login = loginEnter.getText().toString();
            password = passwordEnter.getText().toString();
            if (!login.isEmpty() || !password.isEmpty())
            {
                registrationPresenter.register(this, login, password);
            }
            else
            {
                Toast.makeText(this, "Fields empty", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onRegistrationSuccess(FirebaseUser firebaseUser) {
        Toast.makeText(getApplicationContext(), "Successfully registration", Toast.LENGTH_SHORT).show();
        Intent goToLogin = new Intent(this, LoginActivity.class);
        startActivity(goToLogin);
    }

    @Override
    public void onRegistrationFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
