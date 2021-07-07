package com.myapplication.passosyncapp2.core;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.myapplication.passosyncapp2.interfaces.Constants;
import com.myapplication.passosyncapp2.interfaces.RetrofitInterface;
import com.myapplication.passosyncapp2.models.NewsModel;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsRepository implements Constants {
  public Gson gson;
  public RetrofitInterface api;
  public Call<ResponseBody> call = null;
  public MutableLiveData<List<NewsModel.Article>> newsLiveData;

  public NewsRepository() {
    newsLiveData = new MutableLiveData<>();
    gson = new GsonBuilder().setLenient().create();
    api = retrofitCall().create(RetrofitInterface.class);
  }

  public Retrofit retrofitCall() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.readTimeout(500, TimeUnit.SECONDS);
    builder.connectTimeout(500, TimeUnit.SECONDS);
    OkHttpClient okHttpClient = builder
        .build();
    return new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build();
  }

  public void getNews() {
    call = api.getLatestNews(COUNTRY, CATEGORY, API_KEY);
    call.enqueue(new Callback<ResponseBody>() {
      @Override
      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
          Type type = new TypeToken<NewsModel>() {
          }.getType();
          if (response.code() == 200) {
            NewsModel data = gson.fromJson(response.body().string(), type);
            newsLiveData.postValue(data.getArticles());
          }
        } catch (Exception exception) {
          Log.e("error", exception.getMessage());
        }
      }

      @Override
      public void onFailure(Call<ResponseBody> call, Throwable t) {
        newsLiveData.postValue(null);
      }
    });
  }

  public MutableLiveData<List<NewsModel.Article>> getNewsLiveData() {
    return newsLiveData;
  }
}
