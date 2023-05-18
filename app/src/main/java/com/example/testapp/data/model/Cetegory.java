package com.example.testapp.data.model;

public class Cetegory {
    private String Name;
    private String Image;
    public Cetegory(){

    }

    public Cetegory(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
