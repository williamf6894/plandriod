package com.williamf6894.plandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import java.sql.Time;
import java.util.Date;

/**
 * Created by will on 24/04/16.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "plans.db";
    public static final String TABLE_PLANS = "plans";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TAG = "tag";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TRIGGERDATE = "triggerdate";
    public static final String COLUMN_TRIGGERTIME = "triggertime";
    public static final String COLUMN_REPEATING = "repeating";
    public static final String COLUMN_ALARM = "alarm";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_CUSTOMTIMEREPEAT = "customtimerepeat";
    public static final String COLUMN_NUMBEROFREPETITIONS = "numberofrepetitions";

    /* Make rest of the columns for the DB*/

    public DBHandler (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PLANS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT " +
                COLUMN_TAG + " TEXT " +
                COLUMN_DESCRIPTION + " TEXT " +
                COLUMN_TRIGGERDATE + " DATE " +
                COLUMN_TRIGGERTIME + " DATETIME " +
                COLUMN_REPEATING + " BOOLEAN " +
                COLUMN_ALARM + " BOOLEAN " +
                COLUMN_LOCATION + " TEXT " +
                COLUMN_CUSTOMTIMEREPEAT + " INTEGER " +
                COLUMN_NUMBEROFREPETITIONS + " INTEGER " +
                ");";
        db.execSQL(query);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANS);
        onCreate(db);
    }

    //Add a new Row to the DB
    public void addPlan(PlanItem plan){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, plan.getTitle());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PLANS, null, values);
        db.close();

    }

    //Remove a Row form the DB
    public void deletePlan(int planId){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PLANS + " WHERE " + COLUMN_ID + "= \"" + planId + "\";" );



    }



    //Print toString
    public String toString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PLANS + " WHERE 1";

        //Cursor point to a locations in the results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in the results
        c.moveToFirst();


        while(!c.isAfterLast()){
            if (c.getString(c.getColumnIndex("id")) != null){
                dbString += c.getString(c.getColumnIndex("id"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        c.close();
        db.close();
        return dbString;
    }

}
