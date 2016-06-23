package view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.unitec.jitendrasingh.travelpix.R;
import com.unitec.jitendrasingh.travelpix.model.Travel;
import com.unitec.jitendrasingh.travelpix.model.TravelStorage;

import java.util.List;
import java.util.UUID;

/**
 * Created by jitu on 15/06/16.
 * This is class which makes swiping of fragments
 */
public class TravelPagerActivity extends AppCompatActivity{
    private static final String EXTRA_TRAVEL_ID = "com.unitec.jitendrasingh.travelpix.travel_id";
    private ViewPager mViewPager;
    private List<Travel> mTravels;

    /**
     *
     * @param savedInstanceState : Bundle object which stores the state of the fragment
     */
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

    /**
     *
     * @param packageContext : context object of the application
     * @param travelId : Unique id of the travel object
     * @return static Intent object refrence
     */
    public static Intent newIntent(Context packageContext, UUID travelId){
        Intent intent = new Intent(packageContext, TravelPagerActivity.class);
        intent.putExtra(EXTRA_TRAVEL_ID,travelId);
        return intent;
    }
}
