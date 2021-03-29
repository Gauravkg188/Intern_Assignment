package com.kg.intern_assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Country {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("alpha2Code")
    @Expose
    private String alpha2Code;

    @SerializedName("capital")
    @Expose
    private String capital;

    @SerializedName("flag")
    @Expose
    private String flag;

    @SerializedName("region")
    @Expose
    private String region;

    @SerializedName("subregion")
    @Expose
    private String subregion;

    @SerializedName("population")
    @Expose
    private int population;

    @SerializedName("borders")
    @Expose
    private List<String> borders;


    @SerializedName("languages")
    @Expose
    private List<Languages> languages;

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }


    public List<Languages> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Languages> languages) {
        this.languages = languages;
    }
}
