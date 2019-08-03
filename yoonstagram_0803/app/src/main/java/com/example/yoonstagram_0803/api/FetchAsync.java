package com.example.yoonstagram_0803.api;

import android.os.AsyncTask;
import android.util.Log;

import com.example.yoonstagram_0803.model.PostItem;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FetchAsync extends AsyncTask<String,String, List<PostItem>> {



    @Override
    protected List<PostItem> doInBackground(String... strings) {
            try {
                String url = strings[0];
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = null;
                response = client.newCall(request).execute();

                return Arrays.asList(new Gson().fromJson(response.body().string(), PostItem[].class));

            } catch (IOException e) {

            }


        return null;
    }

    @Override
    protected void onPostExecute(List<PostItem> postItems) {
        super.onPostExecute(postItems);
        Log.d("json", "onPostExecute: " + postItems);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Log.d("json", "onPreExecute: " );

    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

        Log.d("json", "onProgressUpdate: " );
    }
}
