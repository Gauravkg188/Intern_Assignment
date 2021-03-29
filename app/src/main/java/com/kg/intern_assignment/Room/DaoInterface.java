package com.kg.intern_assignment.Room;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;


@Dao
public interface DaoInterface {


        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert( CountryEntity countryEntity);

        @Query("SELECT * FROM Country")
        LiveData<List<CountryEntity>> getCountry();

        @Query("DELETE FROM Country")
        void deleteAllArticles();


        @Query("SELECT COUNT(*) FROM Country")
        int count();

}
