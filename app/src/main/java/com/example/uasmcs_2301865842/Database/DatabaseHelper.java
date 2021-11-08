package com.example.uasmcs_2301865842.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) { super(context, "Dictionary DB", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query1 = "CREATE TABLE WORD(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "wordName TEXT NOT NULL" + ")";
        db.execSQL(query1);

        String query2 = "CREATE TABLE DEFINITIONS(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "imageurl TEXT NOT NULL," +
                "type TEXT NOT NULL," +
                "definition TEXT NOT NULL," +
                "wordName1 TEXT NOT NULL," +
                "FOREIGN KEY (wordName1) REFERENCES WORD (wordName)" + ")";
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS WORD");
        db.execSQL("DROP TABLE IF EXISTS DEFINITIONS");
        onCreate(db);
    }
}