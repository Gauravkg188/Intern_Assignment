package com.kg.intern_assignment.Room;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName= "Country",indices = {@Index(value = {"name"},unique = true)})
public class CountryEntity {


    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;

    private String capital;

    private String alpha2code;
    private String region;
  //  @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
   // private byte[] flag;

    private String flag;
    private String subRegion;
    private Integer population;


   /* public CountryEntity(String name, String capital, String alpha2code, String region, String subRegion, Integer population) {

        this.name = name;
        this.capital = capital;
        this.alpha2code = alpha2code;
        this.region = region;
       // this.flag = flag;
        this.subRegion = subRegion;
        this.population = population;
    }



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getAlpha2code() {
        return alpha2code;
    }

    public String getRegion() {
        return region;
    }


    public String getSubRegion() {
        return subRegion;
    }

    public Integer getPopulation() {
        return population;
    }*/

     @TypeConverters({Converters.class})
    private List<String> borders;

    @TypeConverters({LanguageConverters.class})
    private List<LanguageEntity> languages;



    public CountryEntity(String name, String capital, String alpha2code, String region, String flag, String subRegion, Integer population, List<String> borders, List<LanguageEntity> languages) {

        this.name = name;
        this.capital = capital;
        this.alpha2code = alpha2code;
        this.region = region;
        this.flag = flag;
        this.subRegion = subRegion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }

    public void setId(int id) {
        this.id = id;
    }




    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getAlpha2code() {
        return alpha2code;
    }

    public String getRegion() {
        return region;
    }

    public String getFlag() {
        return flag;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public Integer getPopulation() {
        return population;
    }

    public List<String> getBorders() {
        return borders;
    }

    public List<LanguageEntity> getLanguages() {
        return languages;
    }
}
