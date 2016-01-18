package com.bignerdranch.android.criminalIntent;

import android.support.v4.app.Fragment;

/**
 * Created by jaimejahuey on 1/14/16.
 */
public class CrimeListActivity extends SingleFragmentActivity
{

    //Our main activity whenver the app launches
    @Override
    protected Fragment createFragment()
    {
        return new CrimeListFragment();
    }
}
