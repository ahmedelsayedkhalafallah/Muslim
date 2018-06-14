package com.example.ahmed.muslam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ahmed on 19/0,5/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "azkar.db";
    private static final int DATABASE_VERSION = 1;
DatabaseHelper dbhelper;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //db quiry
        String quiry="create table if not exists "+contract.EntryData.table_name+ " ("
                +contract.EntryData._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +contract.EntryData.date+" TEXT , "
                +contract.EntryData.fajr+" INTEGER DEFAULT '0',"
                +contract.EntryData.Doha+" INTEGER DEFAULT 0,"
                +contract.EntryData.Zuhr+" INTEGER DEFAULT 0,"
                +contract.EntryData.Asr+" INTEGER  DEFAULT 0,"
                +contract.EntryData.Maghreb+" INTEGER DEFAULT 0,"
                +contract.EntryData.Eshaa+" INTEGER DEFAULT 0,"
                +contract.EntryData.Qiam+" INTEGER DEFAULT 0,"
                +contract.EntryData.Jomaa+" INTEGER DEFAULT 0,"
                +contract.EntryData.Sabah+" INTEGER DEFAULT 0,"
                +contract.EntryData.Masaa+" INTEGER DEFAULT 0,"
                +contract.EntryData.Esteghfar+" INTEGER DEFAULT 0,"
                +contract.EntryData.Hawl+" INTEGER DEFAULT 0,"
                +contract.EntryData.Tasbeeh+" INTEGER DEFAULT 0,"
                +contract.EntryData.Hamd+" INTEGER DEFAULT 0,"
                +contract.EntryData.Tawheed+" INTEGER DEFAULT 0,"
                +contract.EntryData.Takbeer+" INTEGER DEFAULT 0,"
                +contract.EntryData.Quraan+" INTEGER DEFAULT 0,"
                +contract.EntryData.Kahf+" INTEGER DEFAULT 0,"
                +contract.EntryData.Sala+" INTEGER DEFAULT 0,"
                +contract.EntryData.Total+" INTEGER DEFAULT 0"
                +" );";

        db.execSQL(quiry);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("drop table if exists "+contract.EntryData.table_name);
     onCreate(db);
    }



    public boolean insertContact (String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date);
        db.insert(contract.EntryData.table_name, null, contentValues);
        return true;
    }





    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select "+contract.EntryData.date+" from "+contract.EntryData.table_name+"", null );
        res.moveToLast();
        return res;
    }

    public Cursor getData(String s) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select "+ s +" from "+contract.EntryData.table_name+"", null );
        res.moveToLast();
        return res;
    }

    public boolean updateContact (String Colum,DatabaseHelper dbhelper, int inc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //get prev val
        Cursor cursor =dbhelper.getData(Colum);
        int currentVal=cursor.getColumnIndex(Colum);
        String col=cursor.getString(currentVal);
        contentValues.put(Colum,Integer.valueOf(col)+inc);
        db.update(contract.EntryData.table_name, contentValues,null,new String[]{});
        cursor =dbhelper.getData(contract.EntryData.Total);
        currentVal=cursor.getColumnIndex(contract.EntryData.Total);
        col=cursor.getString(currentVal);
        contentValues.put(contract.EntryData.Total,Integer.valueOf(col)+inc);
        db.update(contract.EntryData.table_name, contentValues,null,new String[]{});
        // db.execSQL("update "+contract.EntryData.table_name+" set "+Colum+"="+col+inc+" where "+ contract.EntryData._ID+"="+"max("+ contract.EntryData._ID+");");
        return true;
    }



    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, contract.EntryData.table_name);
        return numRows;
    }


}



