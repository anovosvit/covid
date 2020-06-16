package com.github.anovosvit.covidapp.http;


import com.github.anovosvit.covidapp.model.Example;
import com.github.anovosvit.covidapp.model.GlobalInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {

    @GET("/summary")
    Call<Example> getCountryList();

    @GET("/world/total")
    Call<GlobalInfo> getGlobalInfo();
}
