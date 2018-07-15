package com.example.weatherapp.presenter.mainPage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.example.weatherapp.presenter.mainPage.model.CityWeather;

import java.util.ArrayList;
import java.util.List;

public class CityWheatherAdapter extends RecyclerView.Adapter<CityWheatherAdapter.CityWeatherViewHolder> {
    private List<CityWeather> data ;

    public void updateData(List<CityWeather> newData){
//        data.clear();
//        data.addAll(newData);
//        this.notifyDataSetChanged();
        onNewData(newData);
    }

    public CityWheatherAdapter(List<CityWeather> dataList) {
        data = dataList;
    }

    @NonNull
    @Override
    public CityWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_weather_item, parent, false);

        return new CityWeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityWeatherViewHolder holder, int position) {
        CityWeather cur_data = data.get(position);
        holder.name.setText(cur_data.getCityName());
        holder.temp.setText(cur_data.getTemperature().toString());
    }

    @Override
    public void onBindViewHolder(@NonNull CityWeatherViewHolder holder, int position,List<Object> payloads) {
        if (payloads.isEmpty()){
            super.onBindViewHolder(holder, position, payloads);
        }
        else {
            Bundle o = (Bundle) payloads.get(0);
            for (String key : o.keySet()) {
                if(key.equals("name")){
                    holder.name.setText(data.get(position).getCityName());
                }
                if(key.equals("temp")){
                    holder.temp.setText(data.get(position).getTemperature().toString());
                }
            }
        }
//
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void onNewData(List<CityWeather> newData) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new CityWheatherDiffCallback(newData, data));
        diffResult.dispatchUpdatesTo(this);
        this.data.clear();
        this.data.addAll(newData);
    }

    public class CityWeatherViewHolder extends RecyclerView.ViewHolder {
        public TextView name, temp;

        public CityWeatherViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_city_name);
            temp = (TextView) view.findViewById(R.id.tv_temp);
        }
    }
}
