package com.example.livedemo.retrofit;

import com.example.livedemo.model.BlogWrapper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiService {


    @GET("")
    Call<BlogWrapper> getPopularBlog();

}