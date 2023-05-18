package com.example.testapp;

import com.example.testapp.data.model.Accounts;
import com.example.testapp.data.model.CompleteLessons;
import com.example.testapp.data.model.Heplers;
import com.example.testapp.data.model.Lessons;
import com.example.testapp.data.model.Question;
import com.example.testapp.data.model.Unit;
import com.example.testapp.data.model.Users;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {
    @Multipart
    @FormUrlEncoded

    @GET("g_login")
    Call<List<Post>> getPosts();

    @GET("g_login/{id}")
    Call<List<Post>> getEmail(@Path("id") String postId);

    @POST("p_login")
    Call<Accounts> createPost(@Body Accounts accounts);

    @POST("p_lessonComplete")
    Call<List<CompleteLessons>> lessonComplete(@Body CompleteLessons completeLessons);

// Question
    @POST("p_question/{role}/{token}")
    Call<Question> postQuestion(@Body Question question, @Path("role") String role, @Path("token") String token);

    @PUT("put_question/{id}/{role}/{token}")
    Call<Question> putQuestion(@Body Question question, @Path("id") String questionId, @Path("role") String role, @Path("token") String token);

    @DELETE("d_question/{id}/{role}/{token}")
    Call<Question> deleteQuestion(@Path("id") String questionId, @Path("role") String role, @Path("token") String token);

    @POST("p_login")
    Call<Post> createPost(@FieldMap Map<String, String> fields);

    @GET("g_units")
    Call<List<Unit>> getUnits();
    @GET("g_lessons")
    Call<List<Lessons>> getLessons();
    @GET("g_question/{lid}/{role}/{token}")
    Call<List<Question>> getQuestion(@Path("lid") String lid, @Path("role") String role, @Path("token") String token);

    @GET("g_completed_lessons/{email}")
    Call<List<CompleteLessons>> getCompleteLesson(@Path("email") String email);

    @POST("p_lessonComplete")
    Call<List<CompleteLessons>> postCompleteLesson();

    // --------------------REGISTER----------------------------

    @POST("p_sign_ups/{password}")
    Call<Users> signUp(@Body Users user,@Path("password") String password);
    // --------------------/REGISTER----------------------------

    // --------------------ACCOUNT------------------------------

    @PUT("put_acc")
    Call<Accounts> editAccount(@Body Accounts accounts);
    // --------------------/ACCOUNT------------------------------

    // --------------------HELPERS------------------------------
    @GET("g_help/{words}")
    Call<List<Heplers>> getHelpers(@Path("words") String words);
    // --------------------/HELPERS------------------------------
}
