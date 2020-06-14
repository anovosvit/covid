package com.github.anovosvit.covidapp.countrydetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.github.anovosvit.covidapp.R;
import com.github.anovosvit.covidapp.model.CountryInfo;

public class CountryDetailsActivity extends AppCompatActivity implements CounryDetailsContract.View {

    private TextView countryTextView, totalConfirmedTextView, newConfirmedTextView,
            totalDeathsTextView, newDeathsTextView, totalRecoveredTextView, newRecoveredTextView;

    private CounryDetailsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        initViews();

        Intent mIntent = getIntent();
        String country = mIntent.getStringExtra(getString(R.string.key_country_id));

        presenter = new CountryDetailsPresenter(this);
        presenter.getCurrentCountryInfo(country);

    }

    public void initViews() {
        countryTextView = findViewById(R.id.countryText);
        totalConfirmedTextView = findViewById(R.id.totalConfirmedTextView);
        newConfirmedTextView = findViewById(R.id.newConfirmedTextView);
        totalDeathsTextView = findViewById(R.id.totalDeathsTextView);
        newDeathsTextView = findViewById(R.id.newDeathsTextView);
        totalRecoveredTextView = findViewById(R.id.totalRecoveredTextView);
        newRecoveredTextView = findViewById(R.id.newRecoveredTextView);
    }

    @Override
    public void setDataToViews(CountryInfo currentCountry) {
        countryTextView.setText(currentCountry.getCountryName());
        totalConfirmedTextView.setText(String.valueOf(currentCountry.getTotalConfirmed()));
        newConfirmedTextView.setText(String.valueOf(currentCountry.getNewConfirmed()));
        totalDeathsTextView.setText(String.valueOf(currentCountry.getTotalDeaths()));
        newDeathsTextView.setText(String.valueOf(currentCountry.getNewDeaths()));
        totalRecoveredTextView.setText(String.valueOf(currentCountry.getTotalRecovered()));
        newRecoveredTextView.setText(String.valueOf(currentCountry.getNewRecovered()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }
}