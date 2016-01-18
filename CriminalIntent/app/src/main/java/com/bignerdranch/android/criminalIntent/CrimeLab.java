package com.bignerdranch.android.criminalIntent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by jaimejahuey on 1/14/16.
 */
public class CrimeLab
{
    private ArrayList<Crime> mCrimes;
    private static CrimeLab sCrimeLab;
    private Context mAppContext;

    private CrimeLab(Context appContext)
    {
        mAppContext = appContext;
        //mCrimes = new ArrayList<Crime>();

        mCrimes = new ArrayList<Crime>();

        //Just populating the mCrime list with 100 crimes
        for (int i = 0; i < 10; i++)
        {
            Crime c = new Crime();
            c.setmTitle("Crime #" + i);
            c.setmSolved(i % 2 == 0); // Every other one
            mCrimes.add(c);
        }
    }

    //Having the context parameter alllows the singleton to start activities, access project resources
    //and find the applications private storage, and more
    public static CrimeLab get(Context c)
    {
        if(sCrimeLab==null)
        {
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }

        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes()
    {
        return mCrimes;
    }

    public Crime getCrime(UUID id)
    {
        //Going through the arrayList of crimes to find the crimeId if it exits.
        for(Crime c:mCrimes)
        {
            if(c.getmID().equals(id))
                return c;
        }

        return null;
    }
}
