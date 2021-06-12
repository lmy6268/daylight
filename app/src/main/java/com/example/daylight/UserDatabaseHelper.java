package com.example.daylight;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    //---(Singleton 적용) -------------------------------
    private static UserDatabaseHelper instance;
    public static synchronized UserDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new UserDatabaseHelper(context, "User", null, 1);
        }
        return instance;
    }
    //---------------------------------------------------
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "user";
    public UserDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
