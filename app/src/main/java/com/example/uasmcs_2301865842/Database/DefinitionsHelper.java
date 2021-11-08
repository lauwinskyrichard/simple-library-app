package com.example.uasmcs_2301865842.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.uasmcs_2301865842.Definitions;
import com.example.uasmcs_2301865842.Word;

import java.util.ArrayList;

public class DefinitionsHelper {

    DatabaseHelper databaseHelper;

    public DefinitionsHelper(Context context) { databaseHelper = new DatabaseHelper(context); }

    public void insertItem(String imageURL, String type, String definition, String wordName1) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.execSQL("INSERT INTO DEFINITIONS (imageurl, type, definition, wordName1) " +
                "VALUES('"+imageURL+"', " +
                "       '"+type+"', " +
                "       '"+definition+"', " +
                "       '"+wordName1+"')");
        db.close();
        databaseHelper.close();
    }

    public void deleteDef(String wName) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.execSQL("DELETE FROM DEFINITIONS WHERE wordName1 = '"+wName+"'");
        db.close();
        databaseHelper.close();
    }

    public ArrayList<Definitions> getDefinitions(String wname) {

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM DEFINITIONS WHERE wordName1 = '"+wname+"'", null);
        cursor.moveToFirst();

        ArrayList<Definitions> listDefinitions = null;

        if (cursor.getCount() > 0)
        {
            listDefinitions = new ArrayList<>();

            while (!cursor.isAfterLast())
            {
                int id = cursor.getInt(0);
                String tempImageURL = cursor.getString(cursor.getColumnIndex("imageurl"));
                String tempType = cursor.getString(cursor.getColumnIndex("type"));
                String tempDefinition = cursor.getString(cursor.getColumnIndex("definition"));
                String tempWordName1 = cursor.getString(cursor.getColumnIndex("wordName1"));
                listDefinitions.add(new Definitions(id, tempImageURL, tempType,tempDefinition, tempWordName1));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        databaseHelper.close();
        return  listDefinitions;
    }

    public ArrayList<Definitions> getAllDefinitions() {

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM DEFINITIONS", null);
        cursor.moveToFirst();

        ArrayList<Definitions> listDefinitions = null;

        if (cursor.getCount() > 0)
        {
            listDefinitions = new ArrayList<>();

            while (!cursor.isAfterLast())
            {
                int id = cursor.getInt(0);
                String tempImageURL = cursor.getString(cursor.getColumnIndex("imageurl"));
                String tempType = cursor.getString(cursor.getColumnIndex("type"));
                String tempDefinition = cursor.getString(cursor.getColumnIndex("definition"));
                String tempWordName1 = cursor.getString(cursor.getColumnIndex("wordName1"));
                listDefinitions.add(new Definitions(id, tempImageURL, tempType,tempDefinition, tempWordName1));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        databaseHelper.close();
        return  listDefinitions;
    }
}