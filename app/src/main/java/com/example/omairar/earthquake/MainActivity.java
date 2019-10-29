package com.example.omairar.earthquake;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    TextView startDate;
    TextView endDate;

    String sDAte;
    String eDAte;

    private static int date=-1;


    DatePickerDialog.OnDateSetListener mdateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Log.v("data",dayOfMonth+"/"+month+"/"+year);
            month=month+1;
            if(date==0){
                startDate.setText(dayOfMonth+"/"+month+"/"+year);
                sDAte=year+"-"+month+"-"+dayOfMonth;

                date=-1;
            }else if(date==1){
                endDate.setText(dayOfMonth+"/"+month+"/"+year);
                eDAte=year+"-"+month+"-"+dayOfMonth;
                date=-1;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button clickME = findViewById(R.id.main_button);

        clickME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, EarthquakeList.class);
                i.putExtra("sDATE",sDAte);
                i.putExtra("eDATE",eDAte);
                Log.v("lalli",sDAte+" "+eDAte);
                startActivity(i);
            }
        });


        startDate = (TextView) findViewById(R.id.start_date);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Material_Dialog_MinWidth,
                        mdateSetListener,
                        year,month,day);

                date= 0;


               //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }


        });

        endDate = (TextView) findViewById(R.id.end_date);

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Material_Dialog_MinWidth,
                        mdateSetListener,
                        year,month,day);

                date=1;
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });






    }





}
