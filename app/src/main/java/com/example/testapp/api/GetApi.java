package com.example.testapp.api;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.testapp.ui.Common.Common;
import com.example.testapp.Course;
import com.example.testapp.JsonPlaceHolderApi;
import com.example.testapp.data.model.CompleteLessons;
import com.example.testapp.data.model.Lessons;
import com.example.testapp.data.model.Question;
import com.example.testapp.data.model.Unit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetApi {
    private ArrayList<Course> mCourses = new ArrayList<>();
    private ArrayList<Unit> alUnit = new ArrayList<>();
    private ArrayList<Lessons> alLessons = new ArrayList<>();
    private ArrayList<CompleteLessons> alCompleteLessons = new ArrayList<>();

    ListView lvDanhSach;
    ArrayAdapter adapter_C;
    Course course= null;
    Lessons lesson = null;
    CompleteLessons CompleteLesson = null;

    public void getUnitsList(JsonPlaceHolderApi jsonPlaceHolderApi, ApiCallback callback){
        Call<List<Unit>> call = jsonPlaceHolderApi.getUnits();
        call.enqueue(new Callback<List<Unit>>() {
            @Override
            public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText(Learn.this, "failed1", Toast.LENGTH_SHORT).show();
                }
                List<Unit> units = response.body();

                for (Unit unit: units){

                    alUnit.add(new Unit(unit.getName(), unit.getDescription(), unit.getUid()));
                }
                callback.onUnitsLoaded(alUnit);
            }
            @Override
            public void onFailure(Call<List<Unit>> call, Throwable t) {
                //Toast.makeText(Learn.this, "failed", Toast.LENGTH_SHORT).show();
            }

        });
    }
    public void getLessonsList(JsonPlaceHolderApi jsonPlaceHolderApi, LessonsCallback callback){
        Call<List<Lessons>> call = jsonPlaceHolderApi.getLessons();

        call.enqueue(new Callback<List<Lessons>>() {
            @Override
            public void onResponse(Call<List<Lessons>> call, Response<List<Lessons>> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText(Learn.this, "failed1", Toast.LENGTH_SHORT).show();
                }
                List<Lessons> lessons = response.body();

                for (Lessons lesson: lessons){
                    String content = "";
                    content += "Name: " + lesson.getName() + "\n";
                    content += "Description: " + lesson.getLid();
                    alLessons.add(new Lessons( lesson.getLid(), lesson.getName(), lesson.getUid(), lesson.getExp(), lesson.getHint(), lesson.getHintTitle()));

                }

                // cho nay nguy hiem, neu can nen bat lai serEEent
                //setEvent();
                callback.onLessonsLoaded(alLessons);
            }
            @Override
            public void onFailure(Call<List<Lessons>> call, Throwable t) {
                //Toast.makeText(Learn.this, "failed", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public ArrayList<CompleteLessons> getCompleteLessonsList(JsonPlaceHolderApi jsonPlaceHolderApi){

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

    public void getQuestion(JsonPlaceHolderApi jsonPlaceHolderApi, String lid, QuestionCallback callback){
        ArrayList<String> question = new ArrayList<>();
        ArrayList<String> correctAnswers = new ArrayList<>();
        ArrayList<ArrayList<String>> choices = new ArrayList<ArrayList<String>>();
        ArrayList<Question> questions = new ArrayList<>();
        Call<List<Question>> call = jsonPlaceHolderApi.getQuestion(lid, "user", Common.token);
        call.enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if(!response.isSuccessful()){
                    //question.add("success");
                    //Toast.makeText(LessonActivity.this, "success", Toast.LENGTH_SHORT).show();

                    //Toast.makeText(Learn.this, "failed1", Toast.LENGTH_SHORT).show();
                }
                List<Question> questionss = response.body();
                //question = new ArrayList<>();
                for (Question q: questionss){
                    String content = "";
                    //choices.add(q.getOptions());
                    question.add(q.getQuestion());
                    correctAnswers.add(q.getAnswer());
                    questions.add(new Question(q.getQuestion(), q.getAnswer(), q.getOptions(), q.getLesson(), q.getImg(), q.getId()));
                }
                //loadNewQuestion();
                callback.onQuestionsLoaded(questions);
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                //Toast.makeText(Learn.this, "failed", Toast.LENGTH_SHORT).show();
                question.add("failed");
                //Toast.makeText(LessonActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }

        });
    }


}
