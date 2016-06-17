package com.unitec.jitendrasingh.travelpix;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.unitec.jitendrasingh.travelpix.controller.HostingFragmentActivity;

import java.util.UUID;

public class TravelActivity extends HostingFragmentActivity {
    public static final String EXTRA_TRAVEL_ID = "com.unitec.jitendrasingh.travelpix.travel_id";

    public static Intent newIntent(Context packageContext, UUID travelId){
        Intent intent = new Intent(packageContext,TravelActivity.class);
        intent.putExtra(EXTRA_TRAVEL_ID,travelId);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        UUID travelId = (UUID) getIntent().getSerializableExtra(EXTRA_TRAVEL_ID);
        return TravelFragment.newInstance(travelId);
    }
}
