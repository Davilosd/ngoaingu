package com.example.testapp;

import android.widget.Toast;

import com.example.testapp.api.ApiService;
import com.example.testapp.data.model.Question;
import com.example.testapp.data.model.Unit;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionAnswer {


    //    public static String question []=
//            {
//        "which company owns THE ANDROID?",
//            "WhICH ONE id not the programing language?",
//            "where you are living"
//    };
//    public static int a(){
//        // get api
//        Retrofit retrofit = ApiService.retrofit;
//        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
//        Call<List<Unit>> call = jsonPlaceHolderApi.getUnits();
//        //question = new ArrayList<>();
//        //question.add("asd");
//
//        //
//        call.enqueue(new Callback<List<Unit>>() {
//
//            @Override
//            public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
//                if(!response.isSuccessful()){
//                    //Toast.makeText(Learn.this, "failed1", Toast.LENGTH_SHORT).show();
//                }
//                List<Unit> units = response.body();
//                //question = new ArrayList<>();
//                for (Unit unit: units){
//                    String content = "";
//                    content += "Name: " + unit.getName() + "\n";
//                    content += "Description: " + unit.getDescription();
//                    //unitto.add(new Unit(unit.getName(), unit.getDescription()));
//                    //mCourses.add(new Course(unit.getName(), unit.getDescription()));
//                    //question = new ArrayList<>();
//                    question.add(unit.getName());
//                    System.out.println(question.get(0));
//                    System.out.println(question.size());
//                    //QuestionAnswer.question.add("asdd");
//
//                }
//                //question.add("asd");
//                //setasd();
//            }
//
//            @Override
//            public void onFailure(Call<List<Unit>> call, Throwable t) {
//                //Toast.makeText(Learn.this, "failed", Toast.LENGTH_SHORT).show();
//            }
//
//        });
////        question.add("asd");
////        Retrofit retrofit = ApiService.retrofit;
////        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
////        Call<List<Question>> call = jsonPlaceHolderApi.getQuestion();
////        call.enqueue(new Callback<List<Question>>() {
////
////            @Override
////            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
////                if(!response.isSuccessful()){
////                    //Toast.makeText(Learn.this, "failed1", Toast.LENGTH_SHORT).show();
////                    System.out.println("asd");
////                    question.add("asd");
////                }
////                List<Question> units = response.body();
////
////                for (Question q: units){
////                    String content = "";
////                    content += "Name: " + q.getName() + "\n";
////                    content += "Description: " + q.getDescription();
////                    //unitto.add(new Unit(unit.getName(), unit.getDescription()));
////                    //mCourses.add(new Course(unit.getName(), unit.getDescription()));
////                    //question.add(q.getQuestion());
////                    question.add("asd");
//////                    System.out.println("asd");
////                }
////                //setEvent();
////
////            }
////
////            @Override
////            public void onFailure(Call<List<Question>> call, Throwable t) {
////                //Toast.makeText(Learn.this, "failed", Toast.LENGTH_SHORT).show();
////                System.out.println("asd");
////                question.add("asd");
////            }
////
////        });
//        System.out.println(question.size());
//        return question.size();
//    }
    public static void setasd(){
        System.out.println("lololololololo");
    }
    //    public static List<Course> getQuestionApi(){
//        List<Course> mCourses ;
//        ;
//        return mCourses;
//    }
    //public static String questionapi[] = getQuestionApi();


    public static String choices[][] = {
            {"google", "apple", "Nokia", "samsung"},
            {"java", "kotlin", "notepad", "python"},
            {"dl", "apple", "Nokia", "samsung"}
    };
    //   public static ArrayList<ArrayList<String>> choices;
    public static String correctAnswers[] = {
            "google",
            "notepad",
            "dl"

    };
}
