package com.unitec.jitendrasingh.travelpix;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by jitu on 15/06/16.
 */
public class TravelPagerActivity extends AppCompatActivity{
    private static final String EXTRA_TRAVEL_ID = "com.unitec.jitendrasingh.travelpix.travel_id";
    private ViewPager mViewPager;
    private List<Travel> mTravels;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_pager);

        UUID travelId = (UUID) getIntent().getSerializableExtra(EXTRA_TRAVEL_ID);
        mViewPager = (ViewPager) findViewById(R.id.activity_travel_pager_view_pager);
        mTravels = TravelStorage.get(this).getTravels();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Travel travel = mTravels.get(position);
                return TravelFragment.newInstance(travel.getId());
            }

            @Override
            public int getCount() {
                return mTravels.size();
            }
        });

        for (int i = 0; i < mTravels.size(); i++){
            if(mTravels.get(i).getId().equals(travelId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent(Context packageContext, UUID travelId){
        Intent intent = new Intent(packageContext, TravelPagerActivity.class);
        intent.putExtra(EXTRA_TRAVEL_ID,travelId);
        return intent;
    }
}
