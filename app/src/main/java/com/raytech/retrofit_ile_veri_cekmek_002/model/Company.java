package com.raytech.retrofit_ile_veri_cekmek_002.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Company {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("catchPhrase")
    @Expose
    private String catchPhrase;

    @SerializedName("bs")
    @Expose
    private String bs;


    public String getName() {
        return name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public String getBs() {
        return bs;
    }
}
