package com.example.weatherapp.presenter.mainPage.view;

import android.widget.AutoCompleteTextView;

import com.example.weatherapp.presenter.mainPage.model.CityWeather;

import java.util.List;

public interface MainPresenter {
    void addOnTextChangedObserver(AutoCompleteTextView actv);
    void setData(List<CityWeather> dataList);
}
