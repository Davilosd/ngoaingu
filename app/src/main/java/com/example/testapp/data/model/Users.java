package com.example.testapp.data.model;

public class Users {
    private String email;
    private String fName;
    private String lName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Users(String email, String fName, String lName) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
    }
}
