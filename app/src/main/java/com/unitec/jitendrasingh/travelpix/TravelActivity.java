package com.unitec.jitendrasingh.travelpix;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class TravelActivity extends HostingFragmentActivity {


    @Override
    public Fragment createFragment() {
        return new TravelFragment();
    }
}
