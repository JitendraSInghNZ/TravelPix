package com.unitec.jitendrasingh.travelpix;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.unitec.jitendrasingh.travelpix.model.Travel;
import com.unitec.jitendrasingh.travelpix.model.TravelStorage;
import com.unitec.jitendrasingh.travelpix.photostorehelper.PictureUtilsHelper;

import java.io.File;
import java.util.UUID;

/**
 * Created by jitu on 20/06/16.
 */
public class TravelImageViewerDialogFragment extends DialogFragment{
    private static final String ARG_UUID = "uuid";
    private File mPhotoFile;
    private ImageView mImageView;
    public static TravelImageViewerDialogFragment newInstance(UUID uuid){
        Bundle args = new Bundle();
        args.putSerializable(ARG_UUID,uuid);
        TravelImageViewerDialogFragment travelImageViewerDialogFragment = new TravelImageViewerDialogFragment();
        travelImageViewerDialogFragment.setArguments(args);
        return travelImageViewerDialogFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState){
        UUID uuid = (UUID) getArguments().getSerializable(ARG_UUID);
        Travel travel =  TravelStorage.get(getActivity()).getTravel(uuid);
        mPhotoFile = TravelStorage.get(getActivity()).getPhotoFile(travel);
        Bitmap bitmap = PictureUtilsHelper.getScaledBitmap(mPhotoFile.getPath(),getContext());
        View view = inflater.inflate(R.layout.layout_fullscreen_image_view,container,false);
        mImageView = (ImageView) view.findViewById(R.id.full_image_view);
        mImageView.setImageBitmap(bitmap);
        return view;
        //return new AlertDialog.Builder(getActivity().setContentView(view)).create();
    }
}