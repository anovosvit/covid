package com.github.anovosvit.covidapp.countrylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.anovosvit.covidapp.R;
import com.github.anovosvit.covidapp.countrydetail.CountryDetailsActivity;
import com.github.anovosvit.covidapp.model.CountryInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CovidListActivity extends AppCompatActivity implements CovidContract.View, CountryItemClickListener {

    private static final String TAG = "CovidListActivity";
    private CovidPresenter presenter;

    private RecyclerView recyclerView;
    private ArrayList<CountryInfo> countriesList;
    private ArrayList<CountryInfo> filteredList;
    private CovidAdapter adapter;

    private EditText searchText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_list);
        initLayouts();

        presenter = new CovidPresenter(this);
        presenter.loadCountryList();

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    void filter(String text) {
        if (text == null || text == "") {
            setDataToRecyclerView(countriesList);
            Log.i(TAG, "The initial list is set. Number of countries: " + countriesList.size());
        } else {
            filteredList.clear();
            for (CountryInfo countryInfo : countriesList) {

                if (countryInfo.getCountryName().toLowerCase().contains(text.toLowerCase().trim())) {
                    filteredList.add(countryInfo);
                }
            }

            if (filteredList.size() == 0) {
                Toast.makeText(this, getString(R.string.no_such_country), Toast.LENGTH_SHORT).show();
            }

            Log.i(TAG, "A filtered list is set. Number of countries:" + filteredList.size());

            adapter.setCountries(filteredList);
            adapter.notifyDataSetChanged();
        }
    }

    public void initLayouts() {
        searchText = findViewById(R.id.searchText);
        progressBar = findViewById(R.id.progress);

        recyclerView = findViewById(R.id.recyclerView);
        countriesList = new ArrayList<>();
        filteredList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CovidAdapter(CovidListActivity.this, countriesList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<CountryInfo> countries) {
        countriesList.addAll(countries);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        searchText.setText("");
    }

    @Override
    public void onCountryItemClick(int position) {
        if (position == -1) {
            return;
        }
        Intent detailIntent = new Intent(this, CountryDetailsActivity.class);
        String detailInfo = new Gson().toJson(adapter.getCountries().get(position));
        detailIntent.putExtra(getString(R.string.key_country_id), detailInfo);
        startActivity(detailIntent);
    }
}