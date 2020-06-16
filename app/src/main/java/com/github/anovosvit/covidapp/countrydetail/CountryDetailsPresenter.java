package com.github.anovosvit.covidapp.countrydetail;

import com.github.anovosvit.covidapp.model.CountryInfo;
import com.google.gson.Gson;

public class CountryDetailsPresenter implements CountryDetailsContract.Presenter {

    private CountryDetailsContract.View view;

    public CountryDetailsPresenter(CountryDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void getCurrentCountryInfo(String country) {
        CountryInfo currentCountry = new Gson().fromJson(country, CountryInfo.class);
        view.setDataToViews(currentCountry);
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
