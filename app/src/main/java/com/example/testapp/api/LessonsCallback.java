package com.example.testapp.api;

import com.example.testapp.data.model.Lessons;

import java.util.ArrayList;

public interface LessonsCallback {
    void onLessonsLoaded(ArrayList<Lessons> lessons);
}
