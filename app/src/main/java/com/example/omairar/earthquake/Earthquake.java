package com.example.omairar.earthquake;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Earthquake {

    public double mMagnitude;
    public String mPlace;
    public long mTime;

    public String  mLocationOffset;
    public String mCity;

    public String mDigitalTime;
    public String mDate;

    String dateToDisplay;



    public Earthquake(double mMagnitude,String mPlace, long mTime){

        this.mMagnitude=mMagnitude;
        this.mPlace=mPlace;
        this.mTime=mTime;

        stringSplater();
        timeSpliter();

    }


    public void stringSplater(){

        String[] places= mPlace.split("of");

        mLocationOffset=places[0]+" of";
        mCity=places[1];

    }

    public void timeSpliter(){
        Date date = new Date(mTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy@HH:mm:ss a");

        dateToDisplay= dateFormat.format(date);

        String[] dates = dateToDisplay.split("@");

        mDate=dates[0];

        mDigitalTime=dates[1];




    }

    public String decimalFormatter(){
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String magnitude= decimalFormat.format(mMagnitude);
        return magnitude;
    }



}
