package com.unitec.jitendrasingh.travelpix;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.unitec.jitendrasingh.travelpix.model.Travel;
import com.unitec.jitendrasingh.travelpix.model.TravelStorage;

import java.util.List;

/**
 * Created by jitu on 10/06/16.
 * Hosts the List fragment
 */
public class TravelListFragment extends Fragment{
    private RecyclerView mTravelRecyclerView;
    private TravelAdapter mTravelAdapter;
    public static Context sContext;
    private boolean mSubtitleVisible;
    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    /**
     *
     * @param savedInstanceState : Bundle object which stores the state of the fragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    /**
     *
     * @param item  menu item to set the options
     * @return whether the menu item has been set
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_travel_location:
                Travel travel = new Travel();
                TravelStorage.get(getActivity()).addTravel(travel);
                Intent intent = TravelPagerActivity.newIntent(getActivity(), travel.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * updates the subtitle to indicate total travel locations
     */
    private void updateSubtitle() {
        TravelStorage travelStorage = TravelStorage.get(getActivity());
        int crimeCount = travelStorage.getTravels().size();
        String subtitle = getString(R.string.subtitle_format, crimeCount);
        if(!mSubtitleVisible){
            subtitle = null;
        }
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    /**
     * call back method on resuming the fragment lifecycle
     */
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    /**
     *
     * @param menu : menu layout
     * @param inflater  inflates the menu file
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_travel_location_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if(mSubtitleVisible){
            subtitleItem.setTitle(R.string.hide_subtitle);
        }
        else{
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    /**
     *
     * @param outState : Bundle object which stores the state of the fragment
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    /**
     *
     * @param inflater : inflater to inflate a layout
     * @param container : viewgroup to hold a layout
     * @param savedInstanceState : Bundle object to store the state of the fragment
     * @return View object after inflating a layout
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_travel_list,container,false);
        sContext = getActivity();
        mTravelRecyclerView = (RecyclerView) view.findViewById(R.id.travel_recycler_view);
        mTravelRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }

        updateUI();
        return view;
    }


    /**
     * updates the list for the recycler view
     */
    public void updateUI(){
        TravelStorage travelStorage = TravelStorage.get(getActivity());
        List<Travel> travels = travelStorage.getTravels();

        if(mTravelAdapter == null){
            mTravelAdapter = new TravelAdapter(travels,getActivity());
            mTravelRecyclerView.setAdapter(mTravelAdapter);
        }
        else {
            mTravelAdapter.setTravelLocations(travels);
            mTravelAdapter.notifyDataSetChanged();
        }
        updateSubtitle();

    }
}
