package com.raytech.retrofit_ile_veri_cekmek_002.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    //serializedname ile json verileri ile aynı isimde olması için kullanılır.
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("address")
    @Expose
    //adress classı ile ilişkilendirilir.
    private Address address;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("website")
    @Expose
    private String website;

    @SerializedName("company")
    @Expose
    //company classı ile ilişkilendirilir.
    private Company company;

    // private yaptığımız değişkenler için getter ile
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public Company getCompany() {
        return company;
    }
}
