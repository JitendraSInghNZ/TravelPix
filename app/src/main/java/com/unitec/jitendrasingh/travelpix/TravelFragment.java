package com.unitec.jitendrasingh.travelpix;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.unitec.jitendrasingh.travelpix.model.Travel;
import com.unitec.jitendrasingh.travelpix.model.TravelStorage;
import com.unitec.jitendrasingh.travelpix.photostorehelper.PictureUtilsHelper;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jitus_000 on 3/06/2016.
 */
public class TravelFragment extends Fragment{
    private Travel mTravel;
    public File mPhotoFile;
    private Button mSendButton, mDateButton;
    private RatingBar mRatingBar;
    private ImageView mPhotoImageView;
    private ImageButton mTravelPhotoImageButton;
    private CheckBox mVisitAgainCheckBox;
    private EditText mDescriptionEditText;
    private static final String ARG_TRAVEL_ID = "travel_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_PHOTO = 2;
    private static Bitmap mImageBitmap;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //UUID uuid = (UUID) getActivity().getIntent().getSerializableExtra(TravelActivity.EXTRA_TRAVEL_ID);
        UUID uuid = (UUID) getArguments().getSerializable(ARG_TRAVEL_ID);
        mTravel = TravelStorage.get(getActivity()).getTravel(uuid);
        mPhotoFile = TravelStorage.get(getActivity()).getPhotoFile(mTravel);
        Log.i("Memory Address",String.valueOf(mTravel));
    }

    @Override
    public void onPause(){
        super.onPause();
        TravelStorage.get(getActivity()).updateTravelLocation(mTravel);
    }

    public static TravelFragment newInstance(UUID travelId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TRAVEL_ID, travelId);
        TravelFragment travelFragment = new TravelFragment();
        travelFragment.setArguments(args);
        return travelFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Setup UI and get reference to View Object after inflater inflates the layout
       View view =  UIWidgetReferenceSetUp(inflater,container);
        PackageManager packageManager = getActivity().getPackageManager();



        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(TravelFragment.this.getActivity(),String.valueOf(mTravel.getRating()+mTravel.getDate().toString()+mTravel.isSolved()),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, getAboutTravel());
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.about_travel));
                intent = Intent.createChooser(intent,getString(R.string.about_travel));
                startActivity(intent);
            }
        });




        //mDateButton.setEnabled(false);
        updateTravelDate();

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                TravelDateChooserFragment dialog = TravelDateChooserFragment.newInstance(mTravel.getDate());
                dialog.setTargetFragment(TravelFragment.this,REQUEST_DATE);
                dialog.show(fragmentManager, DIALOG_DATE);
            }
        });


        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mTravel.setRating(rating);
            }
        });
        mRatingBar.setRating(mTravel.getRating());

        mDescriptionEditText.setText(mTravel.getDescription());
        mDescriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mTravel.setDescription(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPhotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   DialogFragment dialogFragment = TravelImageViewerDialogFragment.newInstance(mTravel.getId());
                    dialogFragment.show(getFragmentManager(),"image");
            }
        });



        mVisitAgainCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTravel.setVisitAgain(isChecked);
            }
        });
        mVisitAgainCheckBox.setChecked(mTravel.isVisitAgain());
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        boolean canTakePhoto = mPhotoFile != null && captureImage.resolveActivity(packageManager) != null;
        mTravelPhotoImageButton.setEnabled(canTakePhoto);

        if(canTakePhoto){
            Uri uri = Uri.fromFile(mPhotoFile);
            captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }

        mTravelPhotoImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });
        updatePhotoView();
        return view;
    }

    public void onActivityResult(int requestCode,int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if(requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(TravelDateChooserFragment.EXTRA_DATE);
            mTravel.setDate(date);
            updateTravelDate();
        }
        else if (requestCode == REQUEST_PHOTO){
            updatePhotoView();
        }
    }

    private void updateTravelDate() {
        mDateButton.setText(mTravel.getDate().toString());
    }


    private void updatePhotoView(){

        if(mPhotoFile == null || !mPhotoFile.exists()){
            mPhotoImageView.setImageDrawable(null);
        }
        else {
            Bitmap bitmap = PictureUtilsHelper.getScaledBitmap(mPhotoFile.getPath(), getContext());
            mImageBitmap = bitmap;
            mPhotoImageView.setImageBitmap(bitmap);
        }
    }


    //Inflating the view and setting up the UI widgets
    public View UIWidgetReferenceSetUp(LayoutInflater inflater, ViewGroup container){
        View view = inflater.inflate(R.layout.fragment_travel,container,false);
        mSendButton = (Button)view.findViewById(R.id.travelpix_send);
        mDateButton = (Button)view.findViewById(R.id.travel_date);
        mRatingBar = (RatingBar)view.findViewById(R.id.rating_bar);
        mPhotoImageView = (ImageView)view.findViewById(R.id.travel_photo);
        mVisitAgainCheckBox = (CheckBox)view.findViewById(R.id.visit_again);
        mDescriptionEditText = (EditText) view.findViewById(R.id.travel_description_edit_text);
        mTravelPhotoImageButton = (ImageButton) view.findViewById(R.id.travel_camera);
        return view;
    }

    public String getAboutTravel(){
        String visitAgainJudgement = null;
        if(mTravel.isVisitAgain()){
            visitAgainJudgement = getString(R.string.travel_visit_again);
        }
        else {
            visitAgainJudgement = getString(R.string.travel_not_visit_again);
        }
        String dateFormat = "EEE, MMM dd";
        String dateString = DateFormat.format(dateFormat,mTravel.getDate()).toString();
        String message =  ": "+mTravel.getDescription() + " I visited this place on " + dateString + " , I will rate it " + String.valueOf(mTravel.getRating()) + " out of 5 " + " and " + visitAgainJudgement;
        String aboutTravel = getString(R.string.about_travel,mTravel) + message;
        return aboutTravel;
    }

}


