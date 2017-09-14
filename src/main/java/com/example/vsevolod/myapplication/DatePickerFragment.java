package com.example.vsevolod.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(
                getActivity(),
                this,
                year,
                month,
                day
        );

        return datePickerDialog;
    }

    public void onDateSet(DatePicker view,int year,int monthOfYear,int dayOfMonth){
        TextView txtView=(TextView)getActivity().findViewById(R.id.ReturnDateEdit);
        String yearStr=Integer.toString(year);
        String monthStr=Integer.toString(monthOfYear+1);
        String dayStr=Integer.toString(dayOfMonth);
        if(dayOfMonth<10)
            dayStr="0"+dayStr;
        if(monthOfYear+1<10)
            monthStr="0"+monthStr;
        txtView.setText(yearStr+"-"+monthStr+"-"+dayStr);
    }
}
