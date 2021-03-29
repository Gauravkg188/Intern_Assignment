package com.kg.intern_assignment.data;

import com.kg.intern_assignment.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiInterface {


    @GET("region/asia")
    Call<List<Country>> getData(
    );
}
