package com.example.weatherapp.presenter.mainPage.view;

import com.example.weatherapp.presenter.mainPage.model.CityWeather;

import java.util.List;

public interface MainView {
    void onDataLoaded(List<CityWeather> data);
    void onStartSearch(String query);
}
