package com.example.yoonstagram_0803.api;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.yoonstagram_0803.MainActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LikeAsync extends AsyncTask<Integer,Void,Boolean> {
    @Override
    protected Boolean doInBackground(Integer... integers) {
        Integer id = integers[0];
        Integer like = integers[1];
        try {
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("action","like")
                .addFormDataPart("id", String.valueOf(id))
                .addFormDataPart("like" , String.valueOf(like + 1))
                .build();


        Request request = new Request.Builder()
                .url(API.WRITE_API_URL)
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();
        Response result = null;

            result = client.newCall(request).execute();

            Log.d("result Code", "updateLike: " + result.body().string());




        } catch (IOException e) {
            e.printStackTrace();

        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        if(aBoolean) {
            Log.d( " success", "onPostExecute: " + "성공");

        } else {
            Log.d( " fail", "onPostExecute: " + "실패");

        }
    }
}
