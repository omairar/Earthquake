package com.example.omairar.earthquake;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes){
        super(context,0,earthquakes);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listview =convertView;

        if(listview==null){
            listview=LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list,parent,false);
        }

        Earthquake currentearthquake =  getItem(position);

        TextView magnitudeTV=listview.findViewById(R.id.magnitude_tv);
        magnitudeTV.setText(currentearthquake.decimalFormatter());

        TextView locationP1TV=listview.findViewById(R.id.location_p1);
        locationP1TV.setText(currentearthquake.mLocationOffset);

        TextView locationP2TV=listview.findViewById(R.id.location_p2);
        locationP2TV.setText(currentearthquake.mCity);

        TextView dateTV=listview.findViewById(R.id.date);
        dateTV.setText(currentearthquake.mDate);

        TextView timeTV=listview.findViewById(R.id.time);
        timeTV.setText(currentearthquake.mDigitalTime);



        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTV.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentearthquake.mMagnitude);

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return listview;

    }





    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
