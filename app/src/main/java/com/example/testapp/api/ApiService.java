package com.example.testapp.api;

import com.example.testapp.data.model.Question;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Currency;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiService {
    public static String localHost = "http://192.168.1.7:3000/";

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-mm-dd")
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(localHost)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
