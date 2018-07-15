package com.example.weatherapp.presenter.mainPage.model;

public class CityWeather {
    private int cityId;
    private String cityName;
    private Double temperature;

    public CityWeather(int cityId, String cityName, Double temperature) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.temperature = temperature;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
