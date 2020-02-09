package com.example.businessnewsapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BusinessModelApi {
    @GET("top-headlines")
    Call<WholeNews> getBusinessNews(
            @Query("country") String cont,
            @Query("category") String category,
            @Query("apiKey") String apikey
            );


}
