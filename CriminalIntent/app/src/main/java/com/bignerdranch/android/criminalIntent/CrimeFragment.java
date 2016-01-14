package com.bignerdranch.android.criminalIntent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jaimejahuey on 1/14/16.
 */
public class CrimeFragment extends Fragment
{
    private Crime mCrime;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        //Third parameter is false since this tells the inflator whether we want to add the inflated
        //view to the view's parent. We want to add it to the view in the activitys code. ( so in code and not the xml) 
        View v = inflater.inflate(R.layout.fragment_crime, parent, false );
        return v;
    }

}
