package com.example.newsmvvm.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {

    @GET("sources")
    Call<SourceResponse>getNewaSources(@Query("apiKey") String apiKey,
                                       @Query("language") String language);

    @GET("everything")
    Call<NewsResponse>getNews(@Query("apiKey") String apiKey,
                              @Query("language") String language,
                              @Query("sources") String sources);


}
