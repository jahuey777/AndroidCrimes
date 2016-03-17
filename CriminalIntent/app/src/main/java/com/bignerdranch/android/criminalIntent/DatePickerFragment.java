package com.bignerdranch.android.criminalIntent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by jaimejahuey on 3/14/16.
 */
public class DatePickerFragment extends DialogFragment
{
    public static final String EXTRA_DATE = "date";

    private Date mDate;


    public static DatePickerFragment newInstance(Date date)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        mDate = (Date)getArguments().getSerializable(EXTRA_DATE);

        //Create a Calendar to set the year, month and day using the mDate
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);

        //grrabbing the int values to initialize the datepicker
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

       // DatePicker dp = new  DatePicker(getActivity());

        //Using a layout to inflate the datepicker.
        //If i wanted just a date picker then use code above. This view lets us modify
        //or customize. So if we needed something else then it would be easier to modify the view
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date,null);

        DatePicker datePicker = (DatePicker)v.findViewById(R.id.dialog_date_datepicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //Translate yearn, month, day into a Date Object

                mDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();


                //Update argument to preserve selected value on rotation
                getArguments().putSerializable(EXTRA_DATE, mDate);
            }

        });

        //class that provides a fluent interface for constructing an alerdialog
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
//                .setPositiveButton(android.R.string.ok, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .create();
    }

    //Sends the result back to the crimFragment whenever OK is pressed on the dialog box
    //The onActivityResult method is called in CrimeFragment.
    private void sendResult(int resultCode)
    {
        if(getTargetFragment()==null)
           return;

        Intent i = new Intent();
        i.putExtra(EXTRA_DATE, mDate);
        Log.v("NEWDATE2 " , mDate.toString());


        //Gets the REQUEST_DATE integer from crimeFrabment, so the 0.
        //onActivityResult method is called in CrimeFragment
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);

    }
}
