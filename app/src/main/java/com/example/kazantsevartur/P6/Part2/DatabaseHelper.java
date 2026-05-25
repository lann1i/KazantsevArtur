package com.example.kazantsevartur.P6.Part2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "books.db";
    private static final int DB_VERSION = 1;
    static final String TABLE_BOOKS = "books";
    static final String COL_ID = "id";
    static final String COL_TITLE = "title";
    static final String COL_AUTHOR = "author";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_BOOKS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITLE + " TEXT, " +
                COL_AUTHOR + " TEXT)");
        // Тестовые данные
        db.execSQL("INSERT INTO books (title, author) VALUES ('1984', 'Orwell')");
        db.execSQL("INSERT INTO books (title, author) VALUES ('Dune', 'Herbert')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(db);
    }

    public SQLiteDatabase getReadable() { return getReadableDatabase(); }
    public SQLiteDatabase getWritable() { return getWritableDatabase(); }
}