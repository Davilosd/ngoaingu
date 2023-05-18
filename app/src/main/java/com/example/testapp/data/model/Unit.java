package com.example.testapp.data.model;

import com.example.testapp.ui.Common.Common;
import com.example.testapp.JsonPlaceHolderApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Unit {
    private String name;
    private String description;
    private String uid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public Unit(String name, String description, String uid) {
        this.name = name;
        this.description = description;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return name;
    }

    public ArrayList<Unit> getUnits(JsonPlaceHolderApi jsonPlaceHolderApi){
        ArrayList<Unit> alUnit = new ArrayList<>();
        Call<List<CompleteLessons>> call = jsonPlaceHolderApi.getCompleteLesson(Common.currentUser);
        call.enqueue(new Callback<List<CompleteLessons>>() {
            @Override
            public void onResponse(Call<List<CompleteLessons>> call, Response<List<CompleteLessons>> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText(Learn.this, "failed1", Toast.LENGTH_SHORT).show();
                }
                List<CompleteLessons> cLessons = response.body();
                //System.out.println(response.body());
                for (CompleteLessons cLesson: cLessons){
                    String content = "";
                    content += "Name: " + cLesson.getEmail() + "\n";
                    content += "Description: " + cLesson.getLid();
                    //alUnit.add(new Unit(cLesson.getEmail(), cLesson.getLid(), cLesson.getUid()));
                    //System.out.println(alCompleteLessons);
                }

            }
            @Override
            public void onFailure(Call<List<CompleteLessons>> call, Throwable t) {
                //Toast.makeText(Learn.this, "failed", Toast.LENGTH_SHORT).show();
            }

        });
        return alUnit;
    }

}
