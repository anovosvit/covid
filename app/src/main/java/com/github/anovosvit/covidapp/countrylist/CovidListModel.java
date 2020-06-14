package com.github.anovosvit.covidapp.countrylist;

import android.util.Log;

import com.github.anovosvit.covidapp.http.NetworkService;
import com.github.anovosvit.covidapp.model.CountryInfo;
import com.github.anovosvit.covidapp.model.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidListModel implements CovidContract.Model {

    private final String TAG = "CovidListModel";

    @Override
    public void getMovieList(OnFinishedListener onFinishedListener) {
        NetworkService.getInstance()
                .getJSONApi()
                .getCountryList()
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        Example example = response.body();
                        List<CountryInfo> countries = example.getCountries();
                        Log.d(TAG, "Number of countries received: " + countries.size());
                        onFinishedListener.onFinished(countries);
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Log.e(TAG, t.toString());
                        onFinishedListener.onFailure(t);
                    }
                });
    }
}
