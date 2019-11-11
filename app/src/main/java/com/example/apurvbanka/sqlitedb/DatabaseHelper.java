package com.example.apurvbanka.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Apurv Banka on 27-06-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student3.db";
    public static final String TABLE_NAME = "info";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "COLLEGEID";
    public static final String COL_4 = "EMAIL";
    public static final String COL_5 = "PASSWORD";
    public static final String COL_6 = "CONTACT_NO";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        //SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, COLLEGEID INTEGER UNIQUE,EMAIL TEXT,PASSWORD TEXT,CONTACT_NO INTEGER)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String collegeID, String email, String password, String contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,collegeID);
        contentValues.put(COL_4,email);
        contentValues.put(COL_5,password);
        contentValues.put(COL_6,contact);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
