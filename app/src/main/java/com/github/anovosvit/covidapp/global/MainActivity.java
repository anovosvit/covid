package com.github.anovosvit.covidapp.global;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.anovosvit.covidapp.R;
import com.github.anovosvit.covidapp.countrylist.CovidListActivity;
import com.github.anovosvit.covidapp.model.GlobalInfo;

public class MainActivity extends AppCompatActivity implements GlobalContract.View {

    private TextView globalTotalConfirmed, globalTotalDeaths, globalTotalRecovered;
    private ProgressBar confirmedProgress, deathsProgress, recoveredProgress;

    private static final String TAG = "MainActivity";
    private GlobalPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        presenter = new GlobalPresenter(this);
        presenter.loadGlobalInfo();
    }

    private void initViews() {
        globalTotalConfirmed = findViewById(R.id.globalTotalConfirmed);
        globalTotalDeaths = findViewById(R.id.globalTotalDeaths);
        globalTotalRecovered = findViewById(R.id.globalTotalRecovered);

        confirmedProgress = findViewById(R.id.confirmedProgress);
        deathsProgress = findViewById(R.id.deathsProgress);
        recoveredProgress = findViewById(R.id.recoveredProgress);
    }

    @Override
    public void showProgress() {
        confirmedProgress.setVisibility(View.VISIBLE);
        deathsProgress.setVisibility(View.VISIBLE);
        recoveredProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        confirmedProgress.setVisibility(View.GONE);
        deathsProgress.setVisibility(View.GONE);
        recoveredProgress.setVisibility(View.GONE);
    }

    @Override
    public void setDataToViews(GlobalInfo globalInfo) {
        globalTotalConfirmed.setText(String.valueOf(globalInfo.getTotalConfirmed()));
        globalTotalDeaths.setText(String.valueOf(globalInfo.getTotalDeaths()));
        globalTotalRecovered.setText(String.valueOf(globalInfo.getTotalRecovered()));
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getLocalizedMessage());
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    public void openCountryList(View view) {
        startActivity(new Intent(this, CovidListActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }
}