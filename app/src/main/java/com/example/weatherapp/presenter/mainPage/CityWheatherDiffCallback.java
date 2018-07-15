package com.example.weatherapp.presenter.mainPage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearSmoothScroller;

import com.example.weatherapp.presenter.mainPage.model.CityWeather;

import java.util.List;

public class CityWheatherDiffCallback extends DiffUtil.Callback {
    private List<CityWeather> newList;
    private List<CityWeather> oldList;

    CityWheatherDiffCallback(List<CityWeather> newList, List<CityWeather> oldList){
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return
                newList.get(newItemPosition).getCityName().equals(oldList.get(oldItemPosition).getCityName())
                && newList.get(newItemPosition).getTemperature().equals(oldList.get(oldItemPosition).getTemperature());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        CityWeather newData = newList.get(newItemPosition);
        CityWeather oldData = oldList.get(oldItemPosition);

        Bundle diff = new Bundle();
        if(!newData.getCityName().equals(oldData.getCityName())){
            diff.putString("name", newData.getCityName());
        }
        if(!newData.getTemperature().equals (oldData.getTemperature())){
            diff.putDouble("temp", newData.getTemperature());
        }
        if (diff.size()==0){
            return null;
        }
        return diff;
    }
}
