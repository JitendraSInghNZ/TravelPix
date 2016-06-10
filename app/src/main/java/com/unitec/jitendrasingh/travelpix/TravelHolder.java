package com.unitec.jitendrasingh.travelpix;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jitu on 10/06/16.
 */
public class TravelHolder extends RecyclerView.ViewHolder{
    public TextView mDescriptionTextView;
    public TravelHolder(View itemView){
        super(itemView);
        mDescriptionTextView = (TextView) itemView;
    }
}
