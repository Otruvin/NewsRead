package com.example.newsread.Interactor;

import com.example.newsread.Contracts.AddFavoratiesContract;
import com.example.newsread.model.Article;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewsFavorIntractor implements AddFavoratiesContract.IAddNewsFavorIntractor {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    public void performFirebaseAddNews(Article article, FirebaseUser firebaseUser) {
        databaseReference.child(firebaseUser.getUid().toString())
                .child("Favorites")
                .child(prepareStringForFirebase(article.getTitle())).setValue(article);
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
