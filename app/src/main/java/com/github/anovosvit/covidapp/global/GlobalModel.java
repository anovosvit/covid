package com.github.anovosvit.covidapp.global;

import android.util.Log;

import com.github.anovosvit.covidapp.http.NetworkService;
import com.github.anovosvit.covidapp.model.GlobalInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GlobalModel implements GlobalContract.Model {

    private final String TAG = "GlobalModel";


    @Override
    public void getGlobalInfo(OnFinishedListener onFinishedListener) {
        NetworkService.getInstance()
                .getJSONApi()
                .getGlobalInfo()
                .enqueue(new Callback<GlobalInfo>() {
                    @Override
                    public void onResponse(Call<GlobalInfo> call, Response<GlobalInfo> response) {
                        GlobalInfo globalInfo = response.body();
                        Log.d(TAG, "Global information received: " + globalInfo);
                        onFinishedListener.onFinished(globalInfo);
                    }

                    @Override
                    public void onFailure(Call<GlobalInfo> call, Throwable t) {
                        Log.e(TAG, t.toString());
                        onFinishedListener.onFailure(t);
                    }
                });
    }
}
