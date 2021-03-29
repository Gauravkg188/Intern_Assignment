package com.kg.intern_assignment.data;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kg.intern_assignment.Room.RoomRepository;
import com.kg.intern_assignment.model.Country;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Country>> data;



    public ViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository();


    }

    public LiveData<List<Country>> getData()
    {
        data=repository.getData();
        return data;
    }



}
