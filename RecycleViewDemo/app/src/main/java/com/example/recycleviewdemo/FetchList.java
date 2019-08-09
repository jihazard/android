package com.example.recycleviewdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FetchList extends AsyncTask<String,Void, List<Sport>> {
    String TAG = "FETCHLIST";

    Context context;

    public FetchList(Context context) {
        this.context = context;
    }

    @Override
    protected List<Sport> doInBackground(String... strings) {

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(Api.READ_API)
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
    protected void onProgressUpdate(Void... values) {
        Log.d(TAG, "onProgressUpdate: " + values[0].toString());

          super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<Sport> sports) {
        super.onPostExecute(sports);
        Log.d("response ", "doInBackground: " +sports.toString());
    }
}
