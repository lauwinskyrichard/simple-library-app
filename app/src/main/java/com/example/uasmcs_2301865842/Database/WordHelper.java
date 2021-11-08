package com.example.uasmcs_2301865842.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.uasmcs_2301865842.Word;

import java.util.ArrayList;

public class WordHelper {

    DatabaseHelper databaseHelper;

    public WordHelper(Context context) { databaseHelper = new DatabaseHelper(context); }

    public void insertItem(String wName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.execSQL("INSERT INTO WORD (wordName) " +
                "VALUES('"+wName+"')");
        db.close();
        databaseHelper.close();
    }

    public void deleteWord(int id1) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.execSQL("DELETE FROM WORD WHERE id='"+id1+"'");
        db.close();
        databaseHelper.close();
    }

    public ArrayList<Word> getAllWord() {

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM WORD", null);
        cursor.moveToFirst();

        ArrayList<Word> listWord = null;

        if (cursor.getCount() > 0)
        {
            listWord = new ArrayList<>();

            while (!cursor.isAfterLast())
            {
                int id = cursor.getInt(0);
                String tempWordName = cursor.getString(cursor.getColumnIndex("wordName"));
                listWord.add(new Word(id, tempWordName));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        databaseHelper.close();
        return  listWord;
    }
}