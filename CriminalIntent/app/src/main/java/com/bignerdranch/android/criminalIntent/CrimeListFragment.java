package com.bignerdranch.android.criminalIntent;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jaimejahuey on 1/14/16.
 */
public class CrimeListFragment extends android.support.v4.app.ListFragment
{
    private static final String TAG = "CrimeListFragment";
    private ArrayList<Crime> mCrimes;

    //Calls crimelabs to create an array or list of crimes
    //If it doesn't exist, crimelab creates it.
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        mCrimes = CrimeLab.get(getActivity()).getCrimes();

        //ArrayAdapter<Crime> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1, mCrimes);
        CrimeAdapter adapter = new CrimeAdapter(mCrimes);

        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
       // Crime c = (Crime)(getListAdapter()).getItem(position);
        Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);
        Log.d(TAG, c.getmTitle() + " was clicked");
    }

    private class CrimeAdapter extends ArrayAdapter<Crime>
    {
        public CrimeAdapter(ArrayList<Crime>crimes)
        {
            super(getActivity(),0,crimes);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // if we weren't given a view, inflate one
            if (null == convertView)
            {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_crime, null);
            }

            // configure the view for this Crime
            Crime c = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getmTitle());
            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getmDate().toString());
            CheckBox solvedCheckBox =
                    (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.ismSolved());

            return convertView;
        }
    }
}
