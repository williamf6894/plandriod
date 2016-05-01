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

    private static final int DATABASE_VERSION = 11;
    private static final String DATABASE_NAME = "plans.db";
    public static final String TABLE_PLANSINFO = "plans";
    public static final String TABLE_PLANSREMINDER = "reminders";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_SCHEDID = "SCHEDID";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_TAG = "TAG";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_TRIGGERTIME = "TIME";
    public static final String COLUMN_REPEATING = "REPEATING";
    public static final String COLUMN_REPEATTYPE = "REPEATTYPE";
    public static final String COLUMN_LOCATION = "LOCATION";
    // public static final String COLUMN_CUSTOMTIMEREPEAT = "CUSTOMTIMEREPEAT";
    // public static final String COLUMN_NUMBEROFREPETITIONS = "NUMBEROFREPETITIONS";

    /* Make rest of the columns for the DB*/

    public DBHandler (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE plans ( ID INTEGER PRIMARY KEY AUTOINCREMENT, SCHEDID INT, TITLE TEXT, TAG TEXT, DESCRIPTION TEXT, LOCATION TEXT )");
        db.execSQL("CREATE TABLE reminders ( ID INTEGER PRIMARY KEY, TIME INTEGER, REPEATING INTEGER, REPEATTYPE INTEGER)");
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
        db.close();
    }

    public void updateData(String id, String title, String tag, String description, String location, int schedid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_SCHEDID, schedid);
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_TAG, tag);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_LOCATION, location);
        db.update(TABLE_PLANSINFO, values, "ID = ? ", new String[] { id });
        db.close();
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PLANSINFO, "ID = ?", new String[] {id});
    }

    //Add a new Row to the DB
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PLANSINFO, null);
    }


    public Cursor getIdData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PLANSINFO + " WHERE ID = " + id, null);
    }
    // Make a notebook maker
    // Have a Table for all the sched_ids and a sched name with each

    //Add a new Row to the DB
    public void insertReminder(String id, long time, int numberOfReps, int times){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_TRIGGERTIME, time);
        values.put(COLUMN_REPEATING, numberOfReps);
        values.put(COLUMN_REPEATTYPE, times);
        db.insert(TABLE_PLANSREMINDER, null, values);
        db.close();
    }

    public void updateReminder(String id,long time, int numberOfReps, int times){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_TRIGGERTIME, time);
        values.put(COLUMN_REPEATTYPE, times);
        values.put(COLUMN_REPEATING, numberOfReps);

        db.update(TABLE_PLANSREMINDER, values, "ID = ? ", new String[] { id });
        db.close();
    }

    public Integer deleteReminder(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PLANSREMINDER, "ID = ?", new String[] {id});
    } // Okay

    //Add a new Row to the DB
    public Cursor getAllReminder() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PLANSREMINDER, null);
    } // Might be Okay


    public Cursor getIdReminder(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_PLANSREMINDER + " WHERE ID = " + id, null);
    } // Okay

}
