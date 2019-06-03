package com.example.newsread.Interactor;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.newsread.Contracts.RegistrationContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationInteractor implements RegistrationContract.IRegistrationInteractor {

    private static final String TAG = RegistrationInteractor.class.getSimpleName();
    private RegistrationContract.onRegistrationListener mOnregistrationListener;

    public RegistrationInteractor(RegistrationContract.onRegistrationListener onRegistrationListener)
    {
        this.mOnregistrationListener = onRegistrationListener;
    }

    @Override
    public void performRegistration(Activity activity, String email, String password) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                        {
                            mOnregistrationListener.onFailure(task.getException().getMessage());
                        }
                        else
                        {
                            mOnregistrationListener.onSuccess(task.getResult().getUser());
                        }
                    }
                });
    }
}
