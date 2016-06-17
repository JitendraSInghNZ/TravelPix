package com.unitec.jitendrasingh.travelpix.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.unitec.jitendrasingh.travelpix.R;

/**
 * Created by jitu on 10/06/16.
 */
public abstract class HostingFragmentActivity extends AppCompatActivity{

    public abstract Fragment createFragment();
    @Override
    protected void onCreate(Bundle savedInstanceKey){
        super.onCreate(savedInstanceKey);
        setContentView(R.layout.activity_hosting_fragmentl);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if(fragment == null){
            fragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
    }
}
