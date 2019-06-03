package com.example.newsread.DAO;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.newsread.model.Article;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
@TypeConverters(AuthorConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "qppdb.db";
    private static volatile AppDatabase instance;

    private static final Object LOCK = new Object();

    public abstract ArticleDAO articleDAO();

    public static AppDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            synchronized (LOCK)
            {
                if (instance == null)
                {
                    instance = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }

        return instance;
    }
}
