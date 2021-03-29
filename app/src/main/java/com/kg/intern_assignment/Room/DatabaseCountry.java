package com.kg.intern_assignment.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CountryEntity.class},version = 1,exportSchema = false)
public abstract class DatabaseCountry extends RoomDatabase {

    private static DatabaseCountry Instance;

    private static final int NUMBER_OF_THREADS = 2;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public abstract DaoInterface daoInterface();

    public static synchronized DatabaseCountry getInstance(Context context)
    {
        if(Instance ==null)
        {
            Instance= Room.databaseBuilder(context.getApplicationContext(), DatabaseCountry.class,
                    "databaseCountry")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return Instance;
    }




}
