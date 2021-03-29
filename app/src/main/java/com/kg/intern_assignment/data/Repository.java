package com.kg.intern_assignment.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kg.intern_assignment.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private ApiInterface apiInterface;

    public Repository()
    {

        apiInterface=ApiRetrofit.getRetrofitInstance().create(ApiInterface.class);
    }


    public LiveData<List<Country>> getData() {
        final MutableLiveData<List<Country>> data=new MutableLiveData<>();

        apiInterface.getData().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                if (response.body()!=null)
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

                data.setValue(null);
                Log.d("kitty", "onFailure: "+t.getMessage());
            }
        });

        return data;
    }

}
