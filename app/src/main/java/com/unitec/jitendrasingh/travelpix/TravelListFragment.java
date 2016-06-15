package com.unitec.jitendrasingh.travelpix;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jitu on 10/06/16.
 */
public class TravelListFragment extends Fragment{
    private RecyclerView mTravelRecyclerView;
    private TravelAdapter mTravelAdapter;
    public static Context sContext;

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_travel_list,container,false);
        sContext = getActivity();
        mTravelRecyclerView = (RecyclerView) view.findViewById(R.id.travel_recycler_view);
        mTravelRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    public void updateUI(){
        TravelStorage travelStorage = TravelStorage.get(getActivity());
        List<Travel> travels = travelStorage.getTravels();

        if(mTravelAdapter == null){
            mTravelAdapter = new TravelAdapter(travels,getActivity());
        }
        else {
            mTravelAdapter.notifyDataSetChanged();
        }
        mTravelRecyclerView.setAdapter(mTravelAdapter);
    }
}
