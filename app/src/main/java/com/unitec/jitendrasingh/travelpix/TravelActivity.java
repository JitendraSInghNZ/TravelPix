package com.unitec.jitendrasingh.travelpix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.unitec.jitendrasingh.travelpix.controller.HostingFragmentActivity;

import java.util.UUID;

/**
 * Hosting activity which starts the Travel Activity
 */
public class TravelActivity extends HostingFragmentActivity {
    public static final String EXTRA_TRAVEL_ID = "com.unitec.jitendrasingh.travelpix.travel_id";

    /**
     *
     * @param packageContext  : Application context
     * @param travelId : Unique id of the travel object
     * @return
     */
    public static Intent newIntent(Context packageContext, UUID travelId){
        Intent intent = new Intent(packageContext,TravelActivity.class);
        intent.putExtra(EXTRA_TRAVEL_ID,travelId);

        return intent;
    }

    /**
     *
     * @return Fragment of this hosting activity
     */
    @Override
    public Fragment createFragment() {
        UUID travelId = (UUID) getIntent().getSerializableExtra(EXTRA_TRAVEL_ID);
        return TravelFragment.newInstance(travelId);
    }
}
