package com.bignerdranch.android.criminalIntent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MenuItem;


public class CrimeActivity extends SingleFragmentActivity
{
    //Calling the subclass of singleFragmentActivity
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //using getSupportFragmentManager for HoneyCobmb
        //Or can do the following.
        //FragmentManager fm = getFragmentManager();
        //android.app.Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        //Fragment manager maintains a back stack of fragment transactions that you can navigate
        //Fragment transactions add,remove, detach, or replace fragments in the fragment list


        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if(fragment == null)
        {
            fragment = new CrimeFragment();
            //So creating a new fragment transaction, include one add operation in it, and then commit
            fm.beginTransaction().add(R.id.fragmentContainer,fragment).commit();
        }
    }*/

    @Override
    protected Fragment createFragment()
    {
        return new CrimeFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
