package com.example.yoonstagram_0803.retrofit;

import com.example.yoonstagram_0803.model.PostItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface YoonstarInterface {

    @GET("exec?action=read")
    Call<List<PostItem>> getList();
}
