package com.github.anovosvit.covidapp.countrydetail;


import com.github.anovosvit.covidapp.model.CountryInfo;

public interface CounryDetailsContract {
    interface View {
        void setDataToViews(CountryInfo currentCountry);
    }

    interface Presenter {
        void getCurrentCountryInfo(String country);

        void dropView();
    }
}
