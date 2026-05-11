package com.example.kazantsevartur.P5.Part2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DB_NAME = "films.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_FILMS = "films";

    // Поля таблицы
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_RATE = "rate";
    private static final String COL_GENRE = "genre";
    private static final String COL_YEAR = "year";
    private static final String COL_DIRECTOR = "director";

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_FILMS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT NOT NULL, " +
                COL_RATE + " REAL, " +
                COL_GENRE + " TEXT, " +
                COL_YEAR + " INTEGER, " +
                COL_DIRECTOR + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILMS);
        onCreate(db);
    }

    // CREATE
    public boolean addFilm(String name, float rate, String genre, int year, String director) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_RATE, rate);
        cv.put(COL_GENRE, genre);
        cv.put(COL_YEAR, year);
        cv.put(COL_DIRECTOR, director);
        long result = db.insert(TABLE_FILMS, null, cv);
        db.close();
        return result != -1;
    }

    // READ
    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FILMS, null);
        if (cursor.moveToFirst()) {
            do {
                films.add(new Film(
                        cursor.getInt(0), cursor.getString(1), cursor.getFloat(2),
                        cursor.getString(3), cursor.getInt(4), cursor.getString(5)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return films;
    }

    // SEARCH
    public List<Film> searchByGenre(String genre) {
        List<Film> films = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FILMS +
                " WHERE " + COL_GENRE + " LIKE ?", new String[]{"%" + genre + "%"});
        if (cursor.moveToFirst()) {
            do {
                films.add(new Film(
                        cursor.getInt(0), cursor.getString(1), cursor.getFloat(2),
                        cursor.getString(3), cursor.getInt(4), cursor.getString(5)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return films;
    }

    // UPDATE
    public boolean updateFilm(int id, String name, float rate, String genre, int year, String director) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_RATE, rate);
        cv.put(COL_GENRE, genre);
        cv.put(COL_YEAR, year);
        cv.put(COL_DIRECTOR, director);
        int result = db.update(TABLE_FILMS, cv, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }

    // DELETE
    public boolean deleteFilm(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_FILMS, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }

}
