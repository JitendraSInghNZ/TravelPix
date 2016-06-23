package com.unitec.jitendrasingh.travelpix.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.unitec.jitendrasingh.travelpix.database.TravelLocationBaseHelper;
import com.unitec.jitendrasingh.travelpix.database.TravelLocationCursorWrapper;
import com.unitec.jitendrasingh.travelpix.database.TravelLocationDbSchema.TravelLocationTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jitu on 10/06/16.
 * This is a singleton class so that only one database object exist
 */
public class TravelStorage {
    private static TravelStorage sTravelStorage;
    //private List<Travel> mTravels;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    /**
     *
     * @param context : the context object of the application
     * @return reference this object
     */
    public static TravelStorage get(Context context){
        if(sTravelStorage == null){
            sTravelStorage = new TravelStorage(context);
        }
        return sTravelStorage;
     }
    //private constructor

    /**
     *
     * @param context : the context object of the application
     */
    private TravelStorage(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new TravelLocationBaseHelper(mContext).getWritableDatabase();
        //mTravels = new ArrayList<Travel>();
    }

    /**
     *
     * @return : List of the Travel objects
     */
    public List<Travel> getTravels(){
        //return mTravels;
        //return new ArrayList<>();
        List<Travel> travels = new ArrayList<>();
        TravelLocationCursorWrapper cursor = queryTravelLocations(null,null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                travels.add(cursor.getTravel());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return travels;
    }

    /**
     *
     * @param id: unique id of the travel object
     * @return: the travel object after querying for the current unique id
     */
    public Travel getTravel(UUID id){
        /*for(Travel travel : mTravels){
            if(travel.getId().equals(id)){
                return travel;
            }
        }*/
        //return null;
        TravelLocationCursorWrapper cursorWrapper = queryTravelLocations(TravelLocationTable.Columns.UUID + " = ?",new String[]{id.toString()});
        try{
            if(cursorWrapper.getCount() == 0){
                return null;
            }

            cursorWrapper.moveToFirst();
            return cursorWrapper.getTravel();
        }
        finally {
            cursorWrapper.close();
        }
    }

    /**
     *
     * @param travel : Travel object used to query for the handle to a file
     * @return
     */
    public File getPhotoFile(Travel travel){
        File externalFilesDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if(externalFilesDir == null){
            return null;
        }
        return new File(externalFilesDir, travel.getPhotoFilename());
    }

    /**
     *
     * @param travel : Travel object used to update database
     */
    public void updateTravelLocation(Travel travel){
        String uuidString = travel.getId().toString();
        ContentValues values = getContentValues(travel);

        mDatabase.update(TravelLocationTable.NAME, values, TravelLocationTable.Columns.UUID + " = ?", new String[]{uuidString});
    }

    /**
     *
     * @param whereClause : string  arguments for the where clause
     * @param whereArgs : string array arguments for the where clause
     * @return : returns a wrapper object after querying the database
     */
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

    /**
     *
     * @param travel : Travel object used to get the content values from the application
     * @return Content values
     */
    private static ContentValues getContentValues(Travel travel){
        ContentValues values = new ContentValues();
        values.put(TravelLocationTable.Columns.UUID, travel.getId().toString());
        values.put(TravelLocationTable.Columns.DATE, travel.getDate().getTime());
        values.put(TravelLocationTable.Columns.DESCRIPTION, travel.getDescription());
        values.put(TravelLocationTable.Columns.VISIT_AGAIN, travel.isVisitAgain() ? 1 : 0);
        values.put(TravelLocationTable.Columns.RATING, travel.getRating());
        //values.put(TravelLocationTable.Columns.FRIEND_NAME,travel.getFriendName());
        return values;
    }

    /**
     *
     * @param travel : Travel object used to add the travel object to the database
     */
    public void addTravel(Travel travel){
        //mTravels.add(travel);
        ContentValues values = getContentValues(travel);
        mDatabase.insert(TravelLocationTable.NAME, null, values);
    }
}
