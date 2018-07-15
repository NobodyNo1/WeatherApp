package com.example.weatherapp.presenter.mainPage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.example.weatherapp.presenter.mainPage.model.CityWeather;

import java.util.ArrayList;
import java.util.List;

public class CityWheatherAdapter extends RecyclerView.Adapter<CityWheatherAdapter.CityWheatherViewHolder> {
    private List<CityWeather> data = new ArrayList<>();

    public void updateData(List<CityWeather> newData){
        data.clear();
        data.addAll(newData);
        this.notifyDataSetChanged();
    }

    public CityWheatherAdapter(List<CityWeather> dataList) {
        data = dataList;
    }

    @NonNull
    @Override
    public CityWheatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_weather_item, parent, false);

        return new CityWheatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CityWheatherViewHolder holder, int position) {
        CityWeather cur_data = data.get(position);
        holder.name.setText(cur_data.getCityName());
        holder.temp.setText(cur_data.getTemperature().toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CityWheatherViewHolder extends RecyclerView.ViewHolder {
        public TextView name, temp;

        public CityWheatherViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_city_name);
            temp = (TextView) view.findViewById(R.id.tv_temp);
        }
    }
}
