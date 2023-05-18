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

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-mm-dd")
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.11:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
