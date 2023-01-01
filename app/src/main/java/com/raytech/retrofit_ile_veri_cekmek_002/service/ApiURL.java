package com.raytech.retrofit_ile_veri_cekmek_002.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiURL {

    //Bu alan, Retrofit nesnesini tutar. Başlangıçta, null olarak atanır.
    private static Retrofit retrofit = null;

    //Bu alan, sunucu adresini tutar.
    private static String BASE_URL = "https://jsonplaceholder.typicode.com/";

    //Bu alan, JSON verilerini okumak için kullanılır.
    private static Gson gson = new GsonBuilder().setLenient().create();

    //Bu metod, Retrofit nesnesini döndürür. getclient () metodunu çağırdığınızda, Retrofit nesnesi oluşturulur.
    public static Retrofit getClient() {
        //Eğer Retrofit nesnesi null ise, Retrofit nesnesini oluşturur.
        if (retrofit == null) {
            //Retrofit nesnesini oluşturur.
            retrofit = new Retrofit.Builder()
                    //Sunucu adresini belirler.
                    .baseUrl(BASE_URL)
                    //JSON verilerini okumak için GsonConverterFactory sınıfını kullanır.
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    //OkHttp3 kütüphanesini kullanır. Bu kütüphane, HTTP isteklerini yapar.
                    .client(new OkHttpClient())
                    .build();
            //Retrofit nesnesini döndürür.
            return retrofit;
        }
        //Retrofit nesnesini döndürür.
        return retrofit;

    } //getClient() metodunun sonu
}
