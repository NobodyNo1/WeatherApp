package com.example.weatherapp.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static String BASE_URL = "http://api.openweathermap.org/";
    private final WeatherApi whetherApiClient;

    private static RetrofitClient retrofitClient = null;

    public RetrofitClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        whetherApiClient = retrofit.create(WeatherApi.class);
    }

    public static RetrofitClient getRetrofitClient() {
        if(retrofitClient==null)
            retrofitClient = new RetrofitClient();
        return retrofitClient;
    }

    public WeatherApi getWhetherApi() {
        return whetherApiClient;
    }
}
