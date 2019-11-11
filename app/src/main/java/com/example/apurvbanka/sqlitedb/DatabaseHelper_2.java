package com.example.apurvbanka.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;

/**
 * Created by Apurv Banka on 02-07-2018.
 */

public class DatabaseHelper_2 extends SQLiteOpenHelper{

    public static final String DATABASE_NAME_2 = "Student4.db";
    public static final String TABLE_NAME_2 = "code";
    public static final String COLO_1 = "DATE";
    public static final String COLO_2 = "CODE";


    public DatabaseHelper_2(Context context) {
        super(context,DATABASE_NAME_2,null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db2) {
        db2.execSQL("create table " + TABLE_NAME_2 +"(DATE TEXT, CODE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db2, int i, int i1) {
        db2.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME_2);
        onCreate(db2);

    }

    public boolean insertData2(String date, String code){
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLO_1,date);
        contentValues.put(COLO_2,code);
        long result = db2.insert(TABLE_NAME_2, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;


    }

    public Cursor viewData(){
        SQLiteDatabase db2 = this.getWritableDatabase();
        Cursor res2 = db2.rawQuery("select * from "+TABLE_NAME_2,null);
        return res2;
    }
}
