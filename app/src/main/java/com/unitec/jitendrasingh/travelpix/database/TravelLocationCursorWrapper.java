package com.unitec.jitendrasingh.travelpix.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.unitec.jitendrasingh.travelpix.model.Travel;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jitu on 17/06/16.
 * This class provides methods to query database and get back a Travel model class
 */
public class TravelLocationCursorWrapper extends CursorWrapper{

    /**
     *
     * @param cursor
     */
    public TravelLocationCursorWrapper(Cursor cursor){
        super(cursor);
    }

    /**
     *
     * @return : Travel object after querying the SQLite database
     */
    public Travel getTravel(){
        String uuidString = getString(getColumnIndex(TravelLocationDbSchema.TravelLocationTable.Columns.UUID));
        String description = getString(getColumnIndex(TravelLocationDbSchema.TravelLocationTable.Columns.DESCRIPTION));
        long date = getLong(getColumnIndex(TravelLocationDbSchema.TravelLocationTable.Columns.DATE));
        int visitAgain = getInt(getColumnIndex(TravelLocationDbSchema.TravelLocationTable.Columns.VISIT_AGAIN));
        float rating = getFloat(getColumnIndex(TravelLocationDbSchema.TravelLocationTable.Columns.RATING));
//        String frienName = getString(getColumnIndex(TravelLocationDbSchema.TravelLocationTable.Columns.FRIEND_NAME));
        Travel travel = new Travel(UUID.fromString(uuidString));
        travel.setDescription(description);
        travel.setDate(new Date(date));
        travel.setVisitAgain(visitAgain != 0);
        travel.setRating(rating);
  //      travel.setFriendName(frienName);
        return travel;
    }
}
