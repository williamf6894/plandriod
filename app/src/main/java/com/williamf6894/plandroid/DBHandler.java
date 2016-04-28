package com.williamf6894.plandroid;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by will on 24/04/16.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 9;
    private static final String DATABASE_NAME = "plans.db";
    public static final String TABLE_PLANSINFO = "plans";
    public static final String TABLE_PLANSREMINDER = "REMINDERS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_SCHEDID = "SCHEDID";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_TAG = "TAG";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_TRIGGERDATE = "TRIGGERDATE";
    public static final String COLUMN_TRIGGERTIME = "TRIGGERTIME";
    // public static final String COLUMN_REPEATING = "REPEATING";
    public static final String COLUMN_ALARM = "ALARM";
    public static final String COLUMN_LOCATION = "LOCATION";
    // public static final String COLUMN_CUSTOMTIMEREPEAT = "CUSTOMTIMEREPEAT";
    // public static final String COLUMN_NUMBEROFREPETITIONS = "NUMBEROFREPETITIONS";

    /* Make rest of the columns for the DB*/

    public DBHandler (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "create table " + TABLE_PLANSINFO + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SCHEDID + " INT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_TAG + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_LOCATION + " TEXT " +
                ");";
                //  +
                // "\nCREATE TABLE " +
                // TABLE_PLANSREMINDER + "(" +
                // COLUMN_ID + " INTEGER, " +
                // COLUMN_TRIGGERDATE + " DATE, " +
                // COLUMN_TRIGGERTIME + " DATETIME, " +
                // COLUMN_ALARM + " BOOLEAN " +
                // ");";

        db.execSQL("CREATE TABLE plans ( ID INTEGER PRIMARY KEY AUTOINCREMENT, SCHEDID INT, TITLE TEXT, TAG TEXT, DESCRIPTION TEXT, LOCATION TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANSREMINDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANSINFO);
        onCreate(db);
    }

    //Add a new Row to the DB
    public void insertData(String title, String tag, String description, String location, int schedid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SCHEDID, schedid);
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_TAG, tag);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_LOCATION, location);
        db.insert(TABLE_PLANSINFO, null, values);
    }


    //Add a new Row to the DB

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_PLANSINFO, null);
        return res;
    }


    public Cursor getIdData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_PLANSINFO + " WHERE ID = " + id, null);
        return res;
    }
    // Make a notebook maker
    // Have a Table for all the sched_ids and a sched name with each

}
