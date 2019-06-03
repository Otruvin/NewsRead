package com.example.newsread.Interactor;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.newsread.Contracts.FavorNewsContract;
import com.example.newsread.model.Article;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class GetFavorIntractor implements FavorNewsContract.IFavorIntractor {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private FavorNewsContract.OnLoadFavorListener finishListener;
    private List<Article> articles = new ArrayList<>();

    public GetFavorIntractor(FavorNewsContract.OnLoadFavorListener onLoadFavorListener)
    {
        this.finishListener = onLoadFavorListener;
    }

    @Override
    public void loadFavorDataFromDatabase(FirebaseUser firebaseUser) {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.child(firebaseUser.getUid()).child("Favorites").getChildren())
                {
                    Article article = postSnapshot.getValue(Article.class);
                    articles.add(article);
                }

                finishListener.onLoadFavorFinished(articles);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                finishListener.onLoadFavorFailure(databaseError);
            }
        });
    }
}
