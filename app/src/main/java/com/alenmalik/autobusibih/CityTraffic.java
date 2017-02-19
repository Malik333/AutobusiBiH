package com.alenmalik.autobusibih;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;

public class CityTraffic extends AppCompatActivity {

    ArrayList<Integer> iconList = new ArrayList<>();    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ActionBar actionBar;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_traffic);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        iconList.add(R.drawable.relation);
        iconList.add(R.drawable.hours);
        iconList.add(R.drawable.price);
        iconList.add(R.drawable.busstation);



        tabLayout.getTabAt(0).setIcon(iconList.get(0));
        tabLayout.getTabAt(1).setIcon(iconList.get(1));
        tabLayout.getTabAt(2).setIcon(iconList.get(2));
        tabLayout.getTabAt(3).setIcon(iconList.get(3));

    }


    /**
     * A placeholder fragment containing a simple view.
     */


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    RelacijaTab relacijaTab = new RelacijaTab();
                    return relacijaTab;
                case 1:
                    SatnicaTab satnicaTab = new SatnicaTab();
                    return satnicaTab;
                case 2:
                    CijenaTab cijenaTab = new CijenaTab();
                    return cijenaTab;
                case 3:
                    StanicaTab stanicaTab = new StanicaTab();
                    return stanicaTab;
            }
            return null;
        }




        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "RELACIJA";
                case 1:
                    return "SATNICA";
                case 2:
                    return "CIJENA";
                case 3:
                    return "STANICA";
            }
            return null;
        }
    }
}
