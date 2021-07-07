package com.myapplication.passosyncapp2.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

  @GET("top-headlines")
  Call<ResponseBody> getLatestNews(@Query("country") String country,
                                   @Query("category") String category,
                                   @Query("apiKey") String apiKey);

}
