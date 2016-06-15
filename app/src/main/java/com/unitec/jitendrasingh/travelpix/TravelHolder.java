package com.unitec.jitendrasingh.travelpix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jitu on 10/06/16.
 */
public class TravelHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView mDescriptionTextView;
    public TextView mDateTextView;
    public CheckBox mVisitAgainCheckBox;
    public ImageView mThumbnailImageView;
    private Travel mTravel;

    public TravelHolder(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);
        mDescriptionTextView = (TextView) itemView.findViewById(R.id.list_item_travel_description_text_view);
        mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
        mVisitAgainCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_visit_again_check_box);
        mThumbnailImageView = (ImageView) itemView.findViewById(R.id.list_item_travel_photo_image_view);
    }

    public void bindTravel(Travel travel){
        mTravel = travel;
        mDescriptionTextView.setText(mTravel.getDescription());
        mDateTextView.setText(mTravel.getDate().toString());
        mVisitAgainCheckBox.setChecked(mTravel.isVisitAgain());
        mThumbnailImageView.setImageResource(R.drawable.ic_launcher);
    }

    @Override
    public void onClick(View v) {
        Intent intent = TravelPagerActivity.newIntent(TravelListFragment.sContext,mTravel.getId());
        //Intent intent = new Intent(TravelListFragment.sContext,TravelActivity.class);
        TravelListFragment.sContext.startActivity(intent);
    }
}
