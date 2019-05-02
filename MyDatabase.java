package com.example.remainder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "remainderDatabase.db";
    public static final String TABLE_NAME = "remainderTable";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "TASK";
    public static final String COL_2 = "DATE";
    public static final String COL_3 = "TIME";;
    public MyDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,TASK TEXT,DATE TEXT,TIME TEXT)"); }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public boolean insertData(String task,String date,String time){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,task);
        contentValues.put(COL_2,date);
        contentValues.put(COL_3,time);

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(result == -1) return false;
        else  return true;
    }
    public Cursor getAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from "+TABLE_NAME,null);
        return cursor;
    }
    public void deleteTask(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_0 + " = " + id;
        sqLiteDatabase.execSQL(query);
    }

}
