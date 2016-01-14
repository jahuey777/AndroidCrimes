package com.bignerdranch.android.criminalIntent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.SimpleDateFormat;

/**
 * Created by jaimejahuey on 1/14/16.
 */
public class CrimeFragment extends Fragment
{
    //Our crime fragment (object), eventually we will have a list of these in a CrimeActivity that
    //will be managed by the FragmentManager
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

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

        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                //left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                mCrime.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                //blank

            }
        });

        mDateButton = (Button) v.findViewById(R.id.crime_date);

        //Changing the format of the date to display.
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM d, yyyy.");
        mDateButton.setText(formatter.format(mCrime.getmDate()).toString());
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                //setting the crime's solved property
                mCrime.setmSolved(isChecked);
            }
        });


        return v;
    }

}
