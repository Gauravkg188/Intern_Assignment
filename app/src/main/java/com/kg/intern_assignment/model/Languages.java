package com.kg.intern_assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Languages {

    @SerializedName("iso639_1")
    @Expose
    private String iso639_1;

    @SerializedName("iso639_2")
    @Expose
    private String iso639_2;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("nativeName")
    @Expose
    private String nativeName;

    public String getIso639_1() {
        return iso639_1;
    }

    public void setIso639_1(String iso639_1) {
        this.iso639_1 = iso639_1;
    }

    public String getIso639_2() {
        return iso639_2;
    }



    public void setIso639_2(String iso639_2) {
        this.iso639_2 = iso639_2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}
