package com.example.testapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiSetting {
    public void ApiSettings(){
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.6:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }
}
