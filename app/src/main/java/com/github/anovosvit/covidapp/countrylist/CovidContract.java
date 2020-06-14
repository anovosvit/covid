package com.github.anovosvit.covidapp.countrylist;

import com.github.anovosvit.covidapp.model.CountryInfo;

import java.util.List;

public interface CovidContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(List<CountryInfo> countryArrayList);

            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener);

    }

    interface View {
        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<CountryInfo> countries);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {
        void loadCountryList();

        void dropView();
    }
}
