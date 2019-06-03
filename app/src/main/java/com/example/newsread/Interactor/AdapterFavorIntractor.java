package com.example.newsread.Interactor;

import android.support.annotation.NonNull;

import com.example.newsread.Contracts.FavoriteDispAdapter;
import com.example.newsread.model.NewsFavoriteAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdapterFavorIntractor implements FavoriteDispAdapter.IFavorAdapterIntractor {

    DatabaseReference databaseReference;

    public AdapterFavorIntractor() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void performDeleteFavorNews(String title, FirebaseUser firebaseUser) {

        databaseReference.child(firebaseUser.getUid()).child("Favorites")
                .child(prepareStringForFirebase(title)).removeValue();

    }

    public String prepareStringForFirebase(String string)
    {
        string = string.replace(".", ",");
        string = string.replace("#", " ");
        string = string.replace("[", " ");
        string = string.replace("]", " ");
        string = string.replace("$", "dollars");
        return string;
    }
}
