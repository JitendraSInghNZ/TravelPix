package com.unitec.jitendrasingh.travelpix;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by jitus_000 on 3/06/2016.
 */
public class TravelFragment extends Fragment{
    private Travel mTravel;
    private Button mSendButton, mDateButton;
    private RatingBar mRatingBar;
    private ImageView mPhotoImageView;
    private CheckBox mVisitAgainCheckBox;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mTravel = new Travel();
        Log.i("Memory Address",String.valueOf(mTravel));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Setup UI and get reference to View Object after inflater inflates the layout
       View view =  UIWidgetReferenceSetUp(inflater,container);




        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(TravelFragment.this.getActivity(),String.valueOf(mTravel.getRating()+mTravel.getDate().toString()+mTravel.isSolved()),Toast.LENGTH_SHORT).show();
            }
        });



        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mDateButton.setEnabled(false);
        mDateButton.setText(mTravel.getDate().toString());

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mTravel.setRating(rating);
            }
        });



        mPhotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        mVisitAgainCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTravel.setVisitAgain(isChecked);
            }
        });

        return view;
    }
    //Inflating the view and setting up the UI widgets
    public View UIWidgetReferenceSetUp(LayoutInflater inflater, ViewGroup container){
        View view = inflater.inflate(R.layout.fragment_travel,container,false);
        mSendButton = (Button)view.findViewById(R.id.travelpix_send);
        mDateButton = (Button)view.findViewById(R.id.travel_date);
        mRatingBar = (RatingBar)view.findViewById(R.id.rating_bar);
        mPhotoImageView = (ImageView)view.findViewById(R.id.travel_photo);
        mVisitAgainCheckBox = (CheckBox)view.findViewById(R.id.visit_again);
        return view;
    }
}
