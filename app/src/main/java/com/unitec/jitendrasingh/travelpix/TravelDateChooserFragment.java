package com.unitec.jitendrasingh.travelpix;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by jitu on 15/06/16.
 */
public class TravelDateChooserFragment extends DialogFragment{

    public Dialog onCreateDialog(Bundle savedInstance){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date_travel,null);
        return new AlertDialog.Builder(getActivity()).setView(view).setTitle(R.string.date_picker_title).setPositiveButton(android.R.string.ok,null).create();
    }
}
