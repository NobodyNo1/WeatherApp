package com.example.weatherapp.presenter.mainPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

import com.example.weatherapp.R;
import com.example.weatherapp.presenter.mainPage.model.CityWeather;
import com.example.weatherapp.presenter.mainPage.view.MainPresenter;
import com.example.weatherapp.presenter.mainPage.view.MainView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.acTv_city_name)
    AutoCompleteTextView acTv_city_name;
    @BindView(R.id.rv_city_list)
    RecyclerView rv_city_list;

    MainPresenter presenter;
    CityWheatherAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenterImpl(this);
        mAdapter = new CityWheatherAdapter(new ArrayList<>());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_city_list.setLayoutManager(mLayoutManager);
        rv_city_list.setItemAnimator(new DefaultItemAnimator());
        rv_city_list.setAdapter(mAdapter);

        presenter.setData( prepareData());
        presenter.addOnTextChangedObserver(acTv_city_name);
    }

    private List<CityWeather> prepareData(){
        List<CityWeather> cityWeatherList = new ArrayList<>() ;
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray jsonArray = obj.getJSONArray("cities");


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String name = jsonObject.getString("name");

                //Add your values in your `ArrayList` as below:
                CityWeather cityWeather = new CityWeather(id,name,0.0);

                cityWeatherList.add(cityWeather);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cityWeatherList;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(R.raw.kzcitylist);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public void onDataLoaded(List<CityWeather> data) {
        mAdapter.updateData(data);
    }
}
