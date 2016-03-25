package com.bignerdranch.android.criminalIntent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by jaimejahuey on 3/14/16.
 */
public class CrimePagerActivity extends FragmentActivity
{
    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        //Setting up the viewPager. The id is in a xlm file ids.
        //Didn't need to create a custom xml, so this is easier.
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);


        mCrimes = CrimeLab.get(this).getCrimes();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int i)
            {
                Crime crime = mCrimes.get(i);
                return CrimeFragment.newInstance(crime.getmID());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        //To display the correct fragment.
        final UUID crimeId = (UUID)getIntent().getSerializableExtra("CrimeId");
        for(int i=0; i <mCrimes.size(); i++){
            if(mCrimes.get(i).getmID().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            } }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i)
            {
                Crime crime = mCrimes.get(i);
                if(crime.getmTitle()!=null)
                {
                    setTitle(crime.getmTitle());
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

}
