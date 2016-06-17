package com.unitec.jitendrasingh.travelpix.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.unitec.jitendrasingh.travelpix.database.TravelLocationDbSchema.TravelLocationTable;

/**
 * Created by jitu on 17/06/16.
 */
public class TravelLocationBaseHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "travelLocationBase.db";

    public TravelLocationBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TravelLocationDbSchema.TravelLocationTable.NAME+"("+" _id integer primary key autoincrement, "+TravelLocationTable.Columns.UUID+", "+TravelLocationTable.Columns.DESCRIPTION+", "+TravelLocationTable.Columns.DATE+", "+TravelLocationTable.Columns.VISIT_AGAIN+", "+TravelLocationTable.Columns.RATING+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
