package com.example.testapp.data.model;

import java.util.ArrayList;

public class Question {
    //private String name ;
   // private String description;
    private String question;
    private String answer;

    private String options[];
    private String lesson;

    private String img;
    private String _id;
    Boolean chon;

    public Question(String question, String answer, String options[], String lesson, String img, String id) {
        this.question = question;
        this.answer = answer;
        this.options = options;
        this.lesson = lesson;
        this.img = img;
        this._id = id;
    }
    public void setChon(Boolean b){ chon=b;}


    public boolean getChon(){ return chon;}

    public String getId() {
        return _id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setOptions(String options[]) {
        this.options = options;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAnswer() {
        return answer;
    }

    public String[] getOptions() {
        return options;
    }

    public String getLesson() {
        return lesson;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", options=" + options +
                ", lesson='" + lesson + '\'' +

                '}';
    }

}

