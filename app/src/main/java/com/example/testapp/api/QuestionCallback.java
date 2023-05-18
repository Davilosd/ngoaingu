package com.example.testapp.api;

import com.example.testapp.data.model.Question;

import java.util.ArrayList;

public interface QuestionCallback {
    void onQuestionsLoaded(ArrayList<Question> questions);

}
