package view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.unitec.jitendrasingh.travelpix.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jitu on 15/06/16.
 * This class will host the Dialog fragment for implementing the calender fragment
 */
public class TravelDateChooserFragment extends DialogFragment{

    private static final String ARG_DATE = "date";

    private DatePicker mDatePicker;

    public static final String EXTRA_DATE = "com.unitec.jitendrasingh.travelpix.date";

    /**
     *
     * @param date : Date object for having date fragment
     * @return : The Travel choosing fragment
     */
    public static TravelDateChooserFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE,date);
        TravelDateChooserFragment travelDateChooserFragment = new TravelDateChooserFragment();
        travelDateChooserFragment.setArguments(args);
        return travelDateChooserFragment;
    }

    /**
     *
     * @param savedInstance : Bundle object which stores the state of the fragment
      * @return : the Dialog for hosting the Date Dialog
     * This idea of date picker from standard google tutorial to figure out the best way to pick the date from a date picker
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstance){
        Date date = (Date) getArguments().getSerializable(ARG_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date_travel,null);
        mDatePicker = (DatePicker) view.findViewById(R.id.dialog_date_date_picker);
        mDatePicker.init(year,month,day,null);

        return new AlertDialog.Builder(getActivity()).setView(view).setTitle(R.string.date_picker_title).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();
                Date date = new GregorianCalendar(year, month, day).getTime();
                sendResult(Activity.RESULT_OK,date);
            }
        }).create();
    }

    /**
     *
     * @param resultCode : int for choosing the call back fragment
     * @param date : date object
     */
    private void sendResult(int resultCode, Date date){
        if(getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE,date);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }
}
