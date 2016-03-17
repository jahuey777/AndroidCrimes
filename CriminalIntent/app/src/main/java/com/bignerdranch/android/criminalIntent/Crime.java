package com.bignerdranch.android.criminalIntent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jaimejahuey on 1/14/16.
 */
public class Crime
{
    private UUID mID;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime()
    {
        //Generate unique identifier
        mID= UUID.randomUUID();
        //Sets the date to the current date by using default constructor

        //formatting the date
        mDate = new Date();

    }

    public Date getmDate()
    {
        return mDate;
    }

    public void setmDate(Date mDate)
    {
        this.mDate = mDate;
    }

    public boolean ismSolved()
    {
        return mSolved;
    }

    public void setmSolved(boolean mSolved)
    {
        this.mSolved = mSolved;
    }

    public void setmTitle(String Title)
    {
        this.mTitle = Title;
    }

    public UUID getmID()
    {

        return mID;
    }

    public String getmTitle()
    {
        return mTitle;
    }

    @Override
    public String toString()
    {
        return mTitle;
    }

}
