package com.unitec.jitendrasingh.travelpix;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

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
        return new TravelFragment();
    }
}
