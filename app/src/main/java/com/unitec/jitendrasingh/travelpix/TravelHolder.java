package com.unitec.jitendrasingh.travelpix;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.unitec.jitendrasingh.travelpix.model.Travel;
import com.unitec.jitendrasingh.travelpix.model.TravelStorage;
import com.unitec.jitendrasingh.travelpix.photostorehelper.PictureUtilsHelper;
import com.unitec.jitendrasingh.travelpix.photostorehelper.ThumbnailPictureUtilsHelper;

import java.io.File;

/**
 * Created by jitu on 10/06/16.
 * Creates ViewHolder after getting count from adapter
 */
public class TravelHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView mDescriptionTextView;
    public TextView mDateTextView;
    public CheckBox mVisitAgainCheckBox;
    public ImageView mThumbnailImageView;
    private Travel mTravel;
    private File mThumbnailFile;

    /**
     *
     * @param itemView : Holds reference to View objects created by view holder
     */
    public TravelHolder(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);
        mDescriptionTextView = (TextView) itemView.findViewById(R.id.list_item_travel_description_text_view);
        mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
        mVisitAgainCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_visit_again_check_box);
        mThumbnailImageView = (ImageView) itemView.findViewById(R.id.list_item_travel_photo_image_view);
    }

    /**
     *
     * @param travel: this method binds the travel object passed from adapter
     */
    public void bindTravel(Travel travel){
        mTravel = travel;
        mDescriptionTextView.setText(mTravel.getDescription());
        mDateTextView.setText(mTravel.getDate().toString());
        mVisitAgainCheckBox.setChecked(mTravel.isVisitAgain());
        mThumbnailFile = TravelStorage.get(TravelListFragment.sContext).getPhotoFile(mTravel);
        if(mThumbnailFile == null || !mThumbnailFile.exists()){
            mThumbnailImageView.setImageResource(R.drawable.ic_launcher);
        }
        else{
            //Bitmap bitmap = PictureUtilsHelper.getScaledBitmap(mThumbnailFile.getPath(), TravelListFragment.sContext);
            Bitmap bitmap = ThumbnailPictureUtilsHelper.decodeSampledBitmapFromResource(mThumbnailFile.getPath(),100,80);
            mThumbnailImageView.setImageBitmap(bitmap);
            //mThumbnailImageView.setImageBitmap();
        }
    }

    /**
     *
     * @param v : view object to pass on click listener on view objects
     */
    @Override
    public void onClick(View v) {
        Intent intent = TravelPagerActivity.newIntent(TravelListFragment.sContext,mTravel.getId());
        //Intent intent = new Intent(TravelListFragment.sContext,TravelActivity.class);
        TravelListFragment.sContext.startActivity(intent);
    }
}
