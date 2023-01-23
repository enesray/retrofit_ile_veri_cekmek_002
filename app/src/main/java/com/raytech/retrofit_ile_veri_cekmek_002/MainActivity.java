package com.raytech.retrofit_ile_veri_cekmek_002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.raytech.retrofit_ile_veri_cekmek_002.model.User;
import com.raytech.retrofit_ile_veri_cekmek_002.service.ApiService;
import com.raytech.retrofit_ile_veri_cekmek_002.service.ApiURL;
import com.raytech.retrofit_ile_veri_cekmek_002.sqlite.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;
    private DatabaseHelper dbHelper;
    ApiURL apiURL = new ApiURL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        retrofit = apiURL.getClient();
        pullDataToSqlite();
    }


    //retrofit ile çekilen verileri sqlite veritabanına aktarmak için
    private void pullDataToSqlite() {
        Log.d("pullDataToSqlite", "Called");
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<User>> call = apiService.getUserList();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body();
                    dbHelper.addUsersWithAddressAndCompany(users);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

}