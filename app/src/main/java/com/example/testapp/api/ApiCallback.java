package com.example.testapp.api;

import com.example.testapp.data.model.Lessons;
import com.example.testapp.data.model.Question;
import com.example.testapp.data.model.Unit;

import java.util.ArrayList;

public interface ApiCallback {
    void onUnitsLoaded(ArrayList<Unit> units);

}

