package com.example.testapp.data.model;

import com.example.testapp.JsonPlaceHolderApi;
import com.example.testapp.api.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Lessons {
    private String lid;
    private String name;
    private String uid;
    private String exp;
    private String hint;
    private String hintTitle;



    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getHintTitle() {
        return hintTitle;
    }

    public void setHintTitle(String hintTitle) {
        this.hintTitle = hintTitle;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public Lessons(String lid, String name, String uid, String exp, String hint, String hintTitle) {
        this.lid = lid;
        this.name = name;
        this.uid = uid;
        this.exp = exp;
        this.hint = hint;
        this.hintTitle = hintTitle;
    }

    @Override
    public String toString() {

        return name  ;
    }

    public ArrayList<Lessons> getLessonList(){
        Retrofit retrofit = ApiService.retrofit;
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        ArrayList<Lessons> alLessons = new ArrayList<>();
        Call<List<Lessons>> call = jsonPlaceHolderApi.getLessons();
        call.enqueue(new Callback<List<Lessons>>() {
            @Override
            public void onResponse(Call<List<Lessons>> call, Response<List<Lessons>> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText(Learn.this, "failed1", Toast.LENGTH_SHORT).show();
                }
                List<Lessons> cLessons = response.body();
                //System.out.println(response.body());
                for (Lessons cLesson: cLessons){

                    alLessons.add(new Lessons(cLesson.getLid(), cLesson.getName(), cLesson.getUid(), cLesson.getExp(), cLesson.getHint(), cLesson.getHintTitle()));
                    //System.out.println(alCompleteLessons);
                }

            }
            @Override
            public void onFailure(Call<List<Lessons>> call, Throwable t) {
                //Toast.makeText(Learn.this, "failed", Toast.LENGTH_SHORT).show();
            }

        });
        return alLessons;
    }
}
