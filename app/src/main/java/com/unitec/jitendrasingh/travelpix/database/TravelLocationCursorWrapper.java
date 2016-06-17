package com.unitec.jitendrasingh.travelpix.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.unitec.jitendrasingh.travelpix.model.Travel;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jitu on 17/06/16.
 */
public class TravelLocationCursorWrapper extends CursorWrapper{


    public TravelLocationCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Travel getTravel(){
        String uuidString = getString(getColumnIndex(TravelLocationDbSchema.TravelLocationTable.Columns.UUID));
        String description = getString(getColumnIndex(TravelLocationDbSchema.TravelLocationTable.Columns.DESCRIPTION));
        long date = getLong(getColumnIndex(TravelLocationDbSchema.TravelLocationTable.Columns.DATE));
        int visitAgain = getInt(getColumnIndex(TravelLocationDbSchema.TravelLocationTable.Columns.VISIT_AGAIN));
        float rating = getFloat(getColumnIndex(TravelLocationDbSchema.TravelLocationTable.Columns.RATING));

        Travel travel = new Travel(UUID.fromString(uuidString));
        travel.setDescription(description);
        travel.setDate(new Date(date));
        travel.setVisitAgain(visitAgain != 0);
        travel.setRating(rating);
        return travel;
    }
}
