package com.example.weatherapp.presenter.mainPage;

import android.gesture.Prediction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AutoCompleteTextView;

import com.example.weatherapp.data.RetrofitClient;
import com.example.weatherapp.presenter.mainPage.model.CityList;
import com.example.weatherapp.presenter.mainPage.model.CityWeather;
import com.example.weatherapp.presenter.mainPage.model.WeatherResponse;
import com.example.weatherapp.presenter.mainPage.view.MainPresenter;
import com.example.weatherapp.presenter.mainPage.view.MainView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenterImpl implements MainPresenter {

    private static final int DELAY_IN_MILLIS = 300;
    private static final int MIN_LENGTH_TO_START = 2;
    private static final String TAG = MainPresenterImpl.class.getSimpleName();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<CityWeather> dataList = new ArrayList<>();
    private MainView view;

    public MainPresenterImpl(MainView view){
        this.view = view;
    }

    @Override
    public void addOnTextChangedObserver(final AutoCompleteTextView autoCompleteTextView) {
        Observable<WeatherResponse> retry = RxTextView.textChangeEvents(autoCompleteTextView)
                .debounce(DELAY_IN_MILLIS, TimeUnit.MILLISECONDS)
                .map(textViewTextChangeEvent -> textViewTextChangeEvent.text().toString())
                .filter(s -> s.length() >= MIN_LENGTH_TO_START)
                .observeOn(Schedulers.io())
                .switchMap(s -> {
                    view.onStartSearch(s);
                    List<CityWeather> similarCities = getSimilarCities(s);
                    StringBuilder id = new StringBuilder();
                    for (CityWeather obj : similarCities) {
                        id.append(String.valueOf(obj.getCityId()));
                        if (!similarCities.get(similarCities.size() - 1).equals(obj)) {
                            id.append(",");
                        }
                    }
                    if (!id.toString().isEmpty())
                        return RetrofitClient.getRetrofitClient().getWhetherApi().getWeather(id.toString());
                    return null;
                })
                .observeOn(AndroidSchedulers.mainThread()).retry();
//
        Disposable onError = retry.subscribe(
                weatherResponse -> {
                    List<CityWeather> list = new ArrayList<>();
                    for (CityList cityList : weatherResponse.getList()) {
                        list.add(new CityWeather(cityList.getId(), cityList.getName(), cityList.getMain().getTemp()));
                    }

                    view.onDataLoaded(list);
                },
                e -> Log.e(TAG, "onError", e));
    }


    @Override
    public void setData(List<CityWeather> dataList) {
        this.dataList.addAll(dataList);
    }

    List<CityWeather> getSimilarCities(String q){
        List<CityWeather> similarData = new ArrayList<>();
        for (CityWeather data :
                dataList) {
            String current_city_name = data.getCityName();
            if(current_city_name.toLowerCase().startsWith(q.toLowerCase())){
                similarData.add(data);
            }
        }

        return similarData;
    }


}
