package view;

import android.support.v4.app.Fragment;

import com.unitec.jitendrasingh.travelpix.controller.HostingFragmentActivity;

/**
 * Created by jitu on 10/06/16.
 * This class hosts the List activity
 */
public class TravelListActivity extends HostingFragmentActivity {
    /**
     *
     * @return : fragment of the list activity
     */
    @Override
    public Fragment createFragment() {
        return new TravelListFragment();
    }
}
