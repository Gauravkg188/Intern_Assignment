package com.kg.intern_assignment.Room;

import android.app.Application;
import android.util.Log;


import androidx.lifecycle.LiveData;

import java.util.List;

public class RoomRepository {

    private DatabaseCountry database;
    private DaoInterface daoInterface;
    private LiveData<List<CountryEntity>> countryList;


    public RoomRepository(Application application)
    {
        database=DatabaseCountry.getInstance(application);
        daoInterface=database.daoInterface();
        countryList=daoInterface.getCountry();
    }

    public void insert(final CountryEntity countryEntity)
    {
        DatabaseCountry.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                daoInterface.insert(countryEntity);

            }
        });
    }



    public void deleteAllArticles()
    {
        DatabaseCountry.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                daoInterface.deleteAllArticles();
            }
        });
    }

    public int size()
    {
        final int[] s = {0};

        DatabaseCountry.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                s[0] =daoInterface.count();
            }
        });

        return s[0];
    }

    public LiveData<List<CountryEntity>> getCountryList() {
        return countryList;
    }

}
