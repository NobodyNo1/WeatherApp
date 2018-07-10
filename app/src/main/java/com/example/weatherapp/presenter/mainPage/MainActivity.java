package com.example.weatherapp.presenter.mainPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;

import com.example.weatherapp.R;
import com.example.weatherapp.presenter.mainPage.view.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.acTv_city_name)
    AutoCompleteTextView acTv_city_name;
    @BindView(R.id.rv_city_list)
    RecyclerView rv_city_list;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenterImpl();

        acTv_city_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.onTextChanged(editable.toString());
            }
        });
    }
}
