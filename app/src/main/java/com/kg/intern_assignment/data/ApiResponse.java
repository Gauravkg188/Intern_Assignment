package com.kg.intern_assignment.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kg.intern_assignment.model.Country;

import java.util.List;

public class ApiResponse {

    @SerializedName("countries")
    @Expose
    private List<Country> countryList;

    public List<Country> getCountryList() {
        return this.countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }
}
