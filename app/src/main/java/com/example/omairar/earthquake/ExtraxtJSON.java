package com.example.omairar.earthquake;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExtraxtJSON {

    public static ArrayList<Earthquake> extactJSON(String jsonString){

        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(jsonString);
            JSONArray featureArray = root.getJSONArray("features");

            for(int i = 0; i<featureArray.length();i++){
                JSONObject object = featureArray.getJSONObject(i);

                JSONObject properties = object.getJSONObject("properties");

                Double magnitude = properties.getDouble("mag");

                String place = properties.getString("place");

                long time = properties.getLong("time");

                Log.v("lallantop MAG;-- ",Double.toString(magnitude)+place+Long.toString(time));

                Earthquake ef = new Earthquake(magnitude,place,time);

                earthquakes.add(ef);







            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return earthquakes;

    }

}
