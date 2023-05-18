package com.example.testapp.data.model;

public class Heplers {
    private String words ;
    private String from;
    private String to;
    private String meaning;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Heplers(String words, String from, String to, String meaning) {
        this.words = words;
        this.from = from;
        this.to = to;
        this.meaning = meaning;
    }
}
