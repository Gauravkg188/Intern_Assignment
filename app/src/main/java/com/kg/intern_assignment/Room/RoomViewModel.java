package com.kg.intern_assignment.Room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;



import java.util.List;

public class RoomViewModel extends AndroidViewModel {

    private RoomRepository repository;
    private LiveData<List<CountryEntity>> data;



    public RoomViewModel(@NonNull Application application) {
        super(application);
        repository=new RoomRepository(application);

        data=repository.getCountryList();

    }

    public LiveData<List<CountryEntity>> getData() {
        return data;
    }

    public void delete()
    {
        repository.deleteAllArticles();
    }

    public int size()
    {
       return repository.size();
    }

    public void insert(CountryEntity countryEntity)
    {
        repository.insert(countryEntity);
    }


}
