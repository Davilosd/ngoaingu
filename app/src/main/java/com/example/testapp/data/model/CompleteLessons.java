package com.example.testapp.data.model;

import com.example.testapp.ui.Common.Common;
import com.example.testapp.JsonPlaceHolderApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompleteLessons {
    private String email;
    private String lid;

    public CompleteLessons(String email, String lid) {
        this.email = email;
        this.lid = lid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public ArrayList<CompleteLessons> getCompleteLessonsList(JsonPlaceHolderApi jsonPlaceHolderApi){
        ArrayList<CompleteLessons> alCompleteLessons = new ArrayList<>();
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
                    alCompleteLessons.add(new CompleteLessons(cLesson.getEmail(), cLesson.getLid()));
                    //System.out.println(alCompleteLessons);
                }

            }
            @Override
            public void onFailure(Call<List<CompleteLessons>> call, Throwable t) {
                //Toast.makeText(Learn.this, "failed", Toast.LENGTH_SHORT).show();
            }

        });
        return alCompleteLessons;
    }
}
