package com.example.recycleviewdemo;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FetchList extends AsyncTask<String,Void, List<Sport>> {



    @Override
    protected List<Sport> doInBackground(String... strings) {

        try {
            OkHttpClient client = new OkHttpClient();
            String URL = strings[0];
            Request request = new Request.Builder()
                    .url(URL)
                    .build();

            Response response = null;
            response = client.newCall(request).execute();

            return Arrays.asList(new Gson().fromJson(response.body().string(), Sport[].class));



        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(List<Sport> sports) {
        super.onPostExecute(sports);
        Log.d("response ", "doInBackground: " +sports.toString());
    }
}
