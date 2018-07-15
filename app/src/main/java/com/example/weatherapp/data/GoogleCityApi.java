package com.example.weatherapp.data;

import com.example.weatherapp.presenter.mainPage.model.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleCityApi {
    @GET("autocomplete/json?types=(cities)&key=AIzaSyDgZtRpv38Mc41XgS9b8vdMcCLUTpiNyIE")
    Observable<WeatherResponse> autocomplete(@Query("input") String str);
}
