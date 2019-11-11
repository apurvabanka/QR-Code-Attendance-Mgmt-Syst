package com.example.apurvbanka.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Apurv Banka on 03-07-2018.
 */

public class DatabaseHelper_3 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME_3 = "Student5.db";
    public static final String TABLE_NAME_3 = "attendence";
    public static final String COL_1 = "COLLEGEID";
    public static final String COL_2 = "DATE";
    public static final String COL_3 = "ATTEND";

    public DatabaseHelper_3(Context context)
    {
        super(context,DATABASE_NAME_3,null,2);

    }

    @Override
    public void onCreate(SQLiteDatabase db3) {
        db3.execSQL("create table "+TABLE_NAME_3+" (COLLEGEID TEXT, DATE TEXT, ATTEND INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db3, int i, int i1) {
        db3.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME_3);
        onCreate(db3);
    }

    public boolean insertData3(String collegeID){
        SQLiteDatabase db3 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String date2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        contentValues.put(COL_1,collegeID);
        contentValues.put(COL_2,date2);
        contentValues.put(COL_3,0);
        long result = db3.insert(TABLE_NAME_3, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor viewData3(){
        SQLiteDatabase db3 = this.getWritableDatabase();
        Cursor res3 = db3.rawQuery("select * from "+TABLE_NAME_3, null);
        return res3;
    }
}
