package com.github.anovosvit.covidapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {

    @SerializedName("Global")
    @Expose
    private GlobalInfo global;
    @SerializedName("Countries")
    @Expose
    private List<CountryInfo> countries = null;
    @SerializedName("Date")
    @Expose
    private String date;

    public GlobalInfo getGlobal() {
        return global;
    }

    public void setGlobal(GlobalInfo global) {
        this.global = global;
    }

    public List<CountryInfo> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryInfo> countries) {
        this.countries = countries;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
