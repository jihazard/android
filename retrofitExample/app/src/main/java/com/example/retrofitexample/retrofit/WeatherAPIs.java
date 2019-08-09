package com.example.retrofitexample.retrofit;

import com.example.retrofitexample.model.WResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.example.retrofitexample.API.BASE_URL_PATH;

public interface WeatherAPIs {
    @GET(BASE_URL_PATH)
    Call<WResponse> getWeatherByCity(@Query("q") String city, @Query("appid") String apiKey);

//    @GET("/data/2.5/{movie_id}/getDetails")
//    Call <T> getMovieDatils(@Path("movie_id") String movieID);
//
//    @POST("/data/2.1")
//    Call < T > postMovieDetails(@Field("userId") String userID, @Field("token") String token);
}
