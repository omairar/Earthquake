package com.example.omairar.earthquake;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class GetJSON {

    public static String getJSONString(String requestURL){
        URL url = urlOK(requestURL);
        String jsonResponse=null;

        try {
            jsonResponse=connectionOK(url);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return jsonResponse;
    }

    private static URL urlOK(String requestURL){
        URL url= null;
        try{
            url = new URL(requestURL);
        }catch (MalformedURLException e){
            Log.v("URL exception",e.toString());
        }

        Log.v("lallan","urlOK()");
        return  url;
    }


    private static String connectionOK(URL url) throws IOException {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String jsonResponse="";


        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            if(urlConnection.getResponseCode()==200){
                inputStream = urlConnection.getInputStream();
                jsonResponse= buildJSON(inputStream);

            }

        }catch (IOException e){
            Log.v("no connection",e.toString());
        }
        finally {
            if (urlConnection!=null){
                urlConnection.disconnect();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
        Log.v("lallan","connectionOK()");

        return jsonResponse;
    }





    private static String buildJSON(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();

        if(inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));

            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();

            while (line != null){
                output.append(line);
                line=reader.readLine();

            }
        }

        Log.v("lallan",output.toString());

        return output.toString();

    }



    private static String builJSON(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }





}
