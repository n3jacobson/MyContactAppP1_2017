package com.example.jacobsonn1604.contactapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jacobsonn1604 on 5/11/2017.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Contacts.db";
    public static final String TABLE_NAME = "Contact_Table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";


    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}