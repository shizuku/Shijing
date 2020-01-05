package com.shizuku.poetry.shijing;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PoetDBHelper extends SQLiteOpenHelper {
    public PoetDBHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int verison){
        super(context,name,factory,verison);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
