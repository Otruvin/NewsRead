package com.example.newsread.DAO;


import android.arch.persistence.room.TypeConverter;

public class AuthorConverter {

    @TypeConverter
    public static String toStringAuthor(Object o)
    {
        return o == null ? null : o.toString();
    }

    @TypeConverter
    public static Object toObject(String s)
    {
        Object object = s;
        return s == null ? null : object;
    }
}
