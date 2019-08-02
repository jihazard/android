package com.example.ex0729_viewer.API;


import android.os.AsyncTask;
import android.util.Log;

import com.example.ex0729_viewer.model.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FetchApi extends AsyncTask<String,Void,  List<Post>> {


    @Override
    protected   List<Post> doInBackground(String... strings) {
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

            /*
             * JacksonCore
             * */
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            List<Post> convertWebSiteModels = mapper.readValue(string, new TypeReference<List<Post>>(){});

            /*
             * Gson
             * */
            Gson gson = new Gson();
            Post[] posts1 = gson.fromJson(string, Post[].class);
            List<Post> posts2 = Arrays.asList(posts1);


            return posts2;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("error", "doInBackground: error " + e);
        }

        return null;
    }


    @Override
    protected void onPostExecute( List<Post> s) {
        super.onPostExecute(s);
        Log.d("json", "onPostExecute: " + s);



    }
}
