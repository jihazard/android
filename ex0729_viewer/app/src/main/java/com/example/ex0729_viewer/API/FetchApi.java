package com.example.ex0729_viewer.API;


import android.os.AsyncTask;
import android.util.Log;

import com.example.ex0729_viewer.adapter.Adapter;
import com.example.ex0729_viewer.model.Post;
import com.example.ex0729_viewer.model.PostItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FetchApi extends AsyncTask<String,Void, String> {
    List<Post> posts ;

    @Override
    protected  String doInBackground(String... strings) {
        try {
            String url = strings[0];
            Log.i("url", "doInBackground: " + url);;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

                Response response = null;

                response = client.newCall(request).execute();
                ResponseBody body = response.body();
                String string = body.string();
                String reader = body.toString();


           // Post[] posts = new Gson().fromJson(reader, Post[].class);
            Log.i("reader" +"//" , "doInBackground: " +string);



//            JSONParser parser = new JSONParser();
//            Object obj = parser.parse( reader );
//            JSONObject jsonObj = (JSONObject) obj;
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("error", "doInBackground: error " + e);
        }

        return null;
    }


    @Override
    protected void onPostExecute( String s) {
        super.onPostExecute(s);
        Log.d("json", "onPostExecute: " + s);



    }
}
