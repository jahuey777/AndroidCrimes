package com.bignerdranch.android.criminalIntent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jaimejahuey on 1/14/16.
 */
public class CrimeFragment extends Fragment
{
    //Our crime fragment (object), eventually we will have a list of these in a CrimeActivity that
    //will be managed by the FragmentManager
    private Crime mCrime = null;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    private Button mTimeButton;

    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        mCrime = new Crime();

    }

    public static CrimeFragment newInstance(UUID crimeId)
    {
        Bundle args = new Bundle();
        args.putSerializable("CrimeId", crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        //Third parameter is false since this tells the inflator whether we want to add the inflated
        //view to the view's parent. We want to add it to the view in the activitys code. ( so in code and not the xml)
        View v = inflater.inflate(R.layout.fragment_crime, parent, false );

        UUID crimeId = (UUID)getArguments().getSerializable("CrimeId");

        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);

        Log.v("crime " , mCrime.toString());

        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getmTitle());
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
        mTimeButton = (Button) v.findViewById(R.id.crime_time);

        //Displaying the date and time.
        updateDate();
        updateTime();

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();

//                DatePickerFragment dialog = new DatePickerFragment();
                //Using the static constructer newInstance
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getmDate());

                //Sets the target and request code
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(fm,DIALOG_DATE);
            }
        });

        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getmDate());

                dialog.setTargetFragment(CrimeFragment.this, REQUEST_TIME);
                    dialog.show(fm, "hi");


            }
        });


        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.ismSolved());
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

    //Whenver the ok button on the diagog for the calendar gets pressed, then this method
    //gets called. The requestcode is set here and then DatePickerFragment "gets" the code in order
    //to send the result back. This is done in the sendResult methoed in DPFragment.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(resultCode!= Activity.RESULT_OK) return;

        if (requestCode == REQUEST_DATE) {
            Date date = (Date)intent
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);

            //On the result of the calendar, the datebutton is updated.
            mCrime.setmDate(date);
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM d, yyyy.");
            Log.v("NEWDATE " , mCrime.getmDate().toString());

//            mDateButton.setText("Changed"); adding

            updateDate();
            updateTime();
        }
        else if (requestCode==REQUEST_TIME)
        {

            Date date = (Date)intent
                    .getSerializableExtra(TimePickerFragment.EXTRA_TIME);

            Log.v("Time ", " hit and date is " + date.toString());

            mCrime.setmDate(date);
            updateTime();
        }
    }

    public void updateDate()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM d, yyyy.");
        mDateButton.setText(formatter.format(mCrime.getmDate()).toString());
    }

    public void updateTime()
    {
        String min = "m:";
        String hour = "h:";
        String sec = "s";

        if(mCrime.getmDate().getSeconds()<10)
        {
            sec = "0s";
        }
        Log.v("Hours", " " + mCrime.getmDate().getHours());
        Log.v("Hoursmins", " " + mCrime.getmDate().getMinutes());
        Log.v("Hourssec", " " + mCrime.getmDate().getMinutes());

        if(mCrime.getmDate().getHours()>12)
        {
            hour = "0h:";
        }
        if(mCrime.getmDate().getMinutes()<10)
        {
            min = "0m:";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(hour + min + sec);
        mTimeButton.setText(formatter.format(mCrime.getmDate().getTime()));

    }

}
