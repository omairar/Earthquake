package com.example.omairar.earthquake;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeList extends AppCompatActivity {

    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=";

    private static final String USGS_P1_URl="&minfelt=50&minmagnitude=1";

    String temp ="2016-01-01&endtime=2016-05-02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake_list);

        String sDAte = getIntent().getStringExtra("sDATE");

        String eDAte = getIntent().getStringExtra("eDATE");

        StringBuilder builder =new StringBuilder();
        builder.append(USGS_REQUEST_URL);

        builder.append(sDAte).append("&endtime=").append(eDAte).append(USGS_P1_URl);



        String fURL = builder.toString();

        Log.v("lulli",fURL);




        EarthquakeAsync task = new EarthquakeAsync();

        task.execute(fURL);

    }

    public void renderUI(ArrayList<Earthquake> earthquakes){

        StringBuilder output = new StringBuilder();

        for (int i= 0; i<earthquakes.size();i++){
            Earthquake e = earthquakes.get(i);

            output.append(Double.toString(e.mMagnitude)).append("|");
            output.append(e.mLocationOffset).append("|").append(e.mCity).append("|");


            output.append(e.mDate).append(" ").append(e.mDigitalTime).append("\n");


        }

        String gg =output.toString();

        Log.v("lallan",gg);


        EarthquakeAdapter adapter = new EarthquakeAdapter(this,earthquakes);

        ListView listView =findViewById(R.id.list);
        listView.setAdapter(adapter);

    }


    private class EarthquakeAsync extends AsyncTask<String , Void , ArrayList<Earthquake> > {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... url) {

            StringBuilder output = new StringBuilder();

            String Json = GetJSON.getJSONString(url[0]);

            ArrayList<Earthquake> earthquakes = ExtraxtJSON.extactJSON(Json);






            return earthquakes;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
            renderUI(earthquakes);

        }


    }
}
