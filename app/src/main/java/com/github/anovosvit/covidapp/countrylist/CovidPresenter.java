package com.github.anovosvit.covidapp.countrylist;

import com.github.anovosvit.covidapp.model.CountryInfo;

import java.util.List;


public class CovidPresenter implements CovidContract.Presenter, CovidContract.Model.OnFinishedListener {


    private CovidContract.View view;
    private CovidContract.Model model;

    public CovidPresenter(CovidContract.View view) {
        this.view = view;
        this.model = new CovidListModel();
    }

    @Override
    public void loadCountryList() {
        if (view != null) {
            view.showProgress();
        }
        model.getMovieList(this);
    }

    @Override
    public void dropView() {
        this.view = null;
    }


    @Override
    public void onFinished(List<CountryInfo> countryArrayList) {
        view.setDataToRecyclerView(countryArrayList);
        if (view != null) {
            view.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        view.onResponseFailure(t);
        if (view != null) {
            view.hideProgress();
        }
    }
}
