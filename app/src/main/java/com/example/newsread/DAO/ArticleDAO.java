package com.example.newsread.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.newsread.model.Article;

import java.util.List;

@Dao
public interface ArticleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticle(Article article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<Article> articles);

    @Query("DELETE FROM articles")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM articles")
    int getCount();

    @Update
    int updateArticles(List<Article> articles);

    @Query("SELECT * FROM articles")
    List<Article> getAllArticles();
}
