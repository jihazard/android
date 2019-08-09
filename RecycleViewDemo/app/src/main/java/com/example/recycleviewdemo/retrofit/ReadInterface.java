package com.example.recycleviewdemo.retrofit;

import com.example.recycleviewdemo.Sport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ReadInterface {
    @GET("exec?action=read")
    Call<List<Sport>> getArticleList();
}
