package com.example.weatherapp.presenter.mainPage;

import com.example.weatherapp.presenter.mainPage.view.MainPresenter;

public class MainPresenterImpl implements MainPresenter{
    @Override
    public void onTextChanged(String text) {
        if(text.length()<2)
            return;

    }
}
