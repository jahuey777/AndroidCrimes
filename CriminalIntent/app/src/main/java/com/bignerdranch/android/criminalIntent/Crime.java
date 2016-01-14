package com.bignerdranch.android.criminalIntent;

import java.util.UUID;

/**
 * Created by jaimejahuey on 1/14/16.
 */
public class Crime
{
    private UUID mID;
    private String mTitle;

    public Crime()
    {
        //Generate unique identifier
        mID= UUID.randomUUID();
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


}
