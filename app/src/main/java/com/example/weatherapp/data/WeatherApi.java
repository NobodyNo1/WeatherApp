package com.example.weatherapp.data;

import com.example.weatherapp.presenter.mainPage.model.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("data/2.5/group?units=metric&appid=debd5815e147d711956903e31432cf66")
    Observable<WeatherResponse> getWeather(@Query("id") String placeId);
}
