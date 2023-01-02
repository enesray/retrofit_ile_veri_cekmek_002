package com.raytech.retrofit_ile_veri_cekmek_002.service;

import com.raytech.retrofit_ile_veri_cekmek_002.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    //Get ile users url adresine istek atar.
    @GET("users")
    //Dönen verileri List<User> tipinde alır. Call<List<User>> tipinde döndürür.
    Call<List<User>> getUserList();
}
