package com.unitec.jitendrasingh.travelpix.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jitus_000 on 8/06/2016.
 * Model class which gives basic structure for the database
 */
public class Travel {
    private String mDescription;
    private UUID mId;
    private float mRating;
    private boolean mVisitAgain;
    private Date mDate;
    //private String mFriendName;

    /**
     * Constructor to create a travel object
     */
    public Travel(){
        this(UUID.randomUUID());
    }

    /**
     *
     * @param id: for referencing uuid for getting Travel object
     */
    public Travel(UUID id){
        mId = id;
        mDate = new Date();
    }

    /**
     *
     * @param date: object to set date of travel
     */
    public void setDate(Date date){
        mDate = date;
    }

    /**
     *
     * @return date object of the travel
     */
    public Date getDate(){
        return mDate;
    }

    /**
     *
     * @return boolean whether the user wants to visit again or not
     */
    public boolean isVisitAgain(){
        return mVisitAgain;
    }

    /**
     *
     * @param visitAgain: boolean use to set whether to visit again or not
     */
    public void setVisitAgain(boolean visitAgain){
        mVisitAgain = visitAgain;
    }

    /**
     *
     * @return float rating: the rating of the place where the user has visited
     */
    public float getRating(){
        return mRating;
    }

    /**
     *
     * @param rating: sets the rating of the place
     */
    public void setRating(float rating){
        mRating = rating;
    }

    /**
     *
     * @param description : sets the description of the travel
     */
    public void setDescription(String description){
        mDescription = description;
    }

    /**
     *
     * @return : the description of travel location
     */
    public String getDescription(){
        return mDescription;
    }

    /**
     *
     * @return : the unique id of the travel location
     */
    public UUID getId(){
        return mId;
    }

    /**
     *
     * @return : the file name of the saved photograph
     */
    public String getPhotoFilename(){
        return "TRAVELPIX_IMG_"+getId().toString()+".jpg";
    }

    //public void setFriendName(String friendName){mFriendName = friendName;}

    //public String getFriendName(){return mFriendName;}
}
