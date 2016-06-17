package com.unitec.jitendrasingh.travelpix.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jitus_000 on 8/06/2016.
 */
public class Travel {
    private String mDescription;
    private UUID mId;
    private float mRating;
    private boolean mVisitAgain;
    private Date mDate;

    public Travel(){
        this(UUID.randomUUID());
    }

    public Travel(UUID id){
        mId = id;
        mDate = new Date();
    }

    public void setDate(Date date){
        mDate = date;
    }

    public Date getDate(){
        return mDate;
    }

    public boolean isVisitAgain(){
        return mVisitAgain;
    }

    public void setVisitAgain(boolean visitAgain){
        mVisitAgain = visitAgain;
    }

    public float getRating(){
        return mRating;
    }

    public void setRating(float rating){
        mRating = rating;
    }

    public void setDescription(String description){
        mDescription = description;
    }

    public String getDescription(){
        return mDescription;
    }

    public UUID getId(){
        return mId;
    }

    public String getPhotoFilename(){
        return "TRAVELPIX_IMG_"+getId().toString()+".jpg";
    }
}
