package com.unitec.jitendrasingh.travelpix;

import android.support.v4.app.Fragment;

/**
 * Created by jitu on 10/06/16.
 */
public class TravelListActivity extends HostingFragmentActivity{
    @Override
    public Fragment createFragment() {
        return new TravelListFragment();
    }
}
