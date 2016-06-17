package com.unitec.jitendrasingh.travelpix;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unitec.jitendrasingh.travelpix.model.Travel;

import java.util.List;

/**
 * Created by jitu on 10/06/16.
 */
public class TravelAdapter extends RecyclerView.Adapter<TravelHolder>{

    private List<Travel> mTravels;
    private Context mContext;
    public TravelAdapter(List<Travel> travels, Context context){
        mTravels = travels;
        mContext = context;
    }

    @Override
    public TravelHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.list_item_travelpix,parent,false);
        return new TravelHolder(view);
    }

    @Override
    public void onBindViewHolder(TravelHolder holder, int position){
        Travel travel = mTravels.get(position);
        holder.bindTravel(travel);
    }

    @Override
    public int getItemCount(){
        return mTravels.size();
    }

    public void setTravelLocations(List<Travel> travels){
        mTravels = travels;
    }
}
