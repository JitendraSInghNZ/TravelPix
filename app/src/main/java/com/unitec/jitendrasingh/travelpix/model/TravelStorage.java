package com.unitec.jitendrasingh.travelpix.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.unitec.jitendrasingh.travelpix.database.TravelLocationBaseHelper;
import com.unitec.jitendrasingh.travelpix.database.TravelLocationCursorWrapper;
import com.unitec.jitendrasingh.travelpix.database.TravelLocationDbSchema.TravelLocationTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jitu on 10/06/16.
 */
public class TravelStorage {
    private static TravelStorage sTravelStorage;
    //private List<Travel> mTravels;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static TravelStorage get(Context context){
        if(sTravelStorage == null){
            sTravelStorage = new TravelStorage(context);
        }
        return sTravelStorage;
     }
    //private constructor
    private TravelStorage(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new TravelLocationBaseHelper(mContext).getWritableDatabase();
        //mTravels = new ArrayList<Travel>();

    }

    public List<Travel> getTravels(){
        //return mTravels;
        return new ArrayList<>();
    }

    public Travel getTravel(UUID id){
        /*for(Travel travel : mTravels){
            if(travel.getId().equals(id)){
                return travel;
            }
        }*/
        return null;
    }

    public void updateTravelLocation(Travel travel){
        String uuidString = travel.getId().toString();
        ContentValues values = getContentValues(travel);

        mDatabase.update(TravelLocationTable.NAME, values, TravelLocationTable.Columns.UUID + " = ?", new String[]{uuidString});
    }

    private TravelLocationCursorWrapper queryTravelLocations(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(TravelLocationTable.NAME,
                                        null,   //Columns - null selects all columns
                                        whereClause,
                                        whereArgs,
                                        null,// groupBy
                                        null,//having
                                        null);//orderBy
        return new TravelLocationCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Travel travel){
        ContentValues values = new ContentValues();
        values.put(TravelLocationTable.Columns.UUID, travel.getId().toString());
        values.put(TravelLocationTable.Columns.DATE, travel.getDate().getTime());
        values.put(TravelLocationTable.Columns.DESCRIPTION, travel.getDescription());
        values.put(TravelLocationTable.Columns.VISIT_AGAIN, travel.isVisitAgain() ? 1 : 0);
        values.put(TravelLocationTable.Columns.RATING, travel.getRating());
        return values;
    }

    public void addTravel(Travel travel){
        //mTravels.add(travel);
        ContentValues values = getContentValues(travel);
        mDatabase.insert(TravelLocationTable.NAME, null, values);
    }
}
