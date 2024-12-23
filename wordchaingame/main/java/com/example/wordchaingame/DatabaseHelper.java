package com.example.wordchaingame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wordchain.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_USERS = "users";
    public static final String COL_ID = "id";
    public static final String COL_PASSWORD = "password";
    public static final String COL_NAME = "name";
    public static final String COL_H_SCORE = "h_score";
    public static final String TABLE_WORDS = "words";
    public static final String COL_WORD = "word";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COL_ID + " TEXT PRIMARY KEY,"
                + COL_PASSWORD + " TEXT,"
                + COL_NAME + " TEXT,"
                + COL_H_SCORE + " INTEGER DEFAULT 0)";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_WORDS_TABLE = "CREATE TABLE " + TABLE_WORDS + "("
                + COL_WORD + " TEXT PRIMARY KEY)";
        db.execSQL(CREATE_WORDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        onCreate(db);
    }

    public boolean checkUser(String id, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COL_ID}, COL_ID + "=? AND " + COL_PASSWORD + "=?", new String[]{id, password}, null, null, null);
        boolean exists = false;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                exists = true;
            }
            cursor.close();
        }
        return exists;
    }

    public boolean checkWord(String word) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_WORDS, new String[]{COL_WORD}, COL_WORD + "=?", new String[]{word}, null, null, null);
        boolean exists = false;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                exists = true;
            }
            cursor.close();
        }
        return exists;
    }

    public String getRandomWord() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_WORD + " FROM " + TABLE_WORDS + " ORDER BY RANDOM() LIMIT 1", null);
        String word = null;
        if (cursor.moveToFirst()) {
            word = cursor.getString(cursor.getColumnIndex(COL_WORD));
        }
        cursor.close();
        return word;
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT rowid AS _id, id, password, name, h_score FROM " + TABLE_USERS;
        return db.rawQuery(query, null);
    }

    public Cursor getAllUsersScore() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT rowid AS _id, id, name, h_score FROM " + TABLE_USERS + " ORDER BY h_score DESC";
        return db.rawQuery(query, null);
    }

    public Cursor getAllWordsCursor() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT rowid AS _id, " + COL_WORD + " FROM " + TABLE_WORDS, null);
    }

    public boolean checkUserExists(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COL_ID}, COL_ID + "=?", new String[]{id}, null, null, null);
        boolean exists = false;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                exists = true;
            }
            cursor.close();
        }
        return exists;
    }

    public void deleteWord(String word) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORDS, COL_WORD + "=?", new String[]{word});
        db.close();
    }

    public String[] getAllWords() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_WORD + " FROM " + TABLE_WORDS, null);
        List<String> words = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                words.add(cursor.getString(cursor.getColumnIndex(COL_WORD)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return words.toArray(new String[0]);
    }

    public void deleteUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, COL_ID + "=?", new String[]{id});
    }

    public String getUserId(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,
                new String[]{COL_ID},
                COL_ID + "=?",
                new String[]{id},
                null, null, null);
        String userId = null;
        if (cursor.moveToFirst()) {
            userId = cursor.getString(cursor.getColumnIndex(COL_ID));
        }
        cursor.close();
        return userId;
    }

    public int getHighScore(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_H_SCORE + " FROM " + TABLE_USERS + " WHERE " + COL_ID + "=?", new String[]{userId});

        int highScore = 0;
        if (cursor.moveToFirst()) {
            highScore = cursor.getInt(cursor.getColumnIndex(COL_H_SCORE));
        }
        cursor.close();
        return highScore;
    }

    public void updateHighScore(String userId, int newScore) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_H_SCORE + " FROM " + TABLE_USERS + " WHERE " + COL_ID + "=?", new String[]{userId});

        if (cursor.moveToFirst()) {
            int currentHighScore = cursor.getInt(cursor.getColumnIndex(COL_H_SCORE));
            if (newScore > currentHighScore) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(COL_H_SCORE, newScore);
                db.update(TABLE_USERS, contentValues, COL_ID + "=?", new String[]{String.valueOf(userId)});
            }
        }
        cursor.close();
        db.close();
    }
}
