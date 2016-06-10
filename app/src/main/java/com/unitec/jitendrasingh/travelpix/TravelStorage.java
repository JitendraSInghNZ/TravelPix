package com.unitec.jitendrasingh.travelpix;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jitu on 10/06/16.
 */
public class TravelStorage {
    private static TravelStorage sTravelStorage;
    private List<Travel> mTravels;


    public static TravelStorage get(Context context){
        if(sTravelStorage == null){
            sTravelStorage = new TravelStorage(context);
        }
        return sTravelStorage;
     }
    //private constructor
    private TravelStorage(Context context){
        mTravels = new ArrayList<Travel>();
        for(int i = 0; i < 100 ; ++i){
            Travel travel = new Travel();
            travel.setDescription("Travel Location "+i);
            travel.setVisitAgain(i % 2 == 0);
            mTravels.add(travel);
        }
    }

    public List<Travel> getTravels(){
        return mTravels;
    }

    private Travel getTravel(UUID id){
        for(Travel travel : mTravels){
            if(travel.getId().equals(id)){
                return travel;
            }
        }
        return null;
    }
}
