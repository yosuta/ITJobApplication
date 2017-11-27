package com.example.lenovo.jobapp1;

import android.graphics.YuvImage;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class User {
    public String name;
    public String bio;
    public String title;
    public String language;
    public String email;
    public String password;
    public String applies;
    public String userType;
    public int userId;

    public User(){
    }
    public User(String name, String bio, String title, String language){
        this.name=name;
        this.bio=bio;
        this.title=title;
        this.language=language;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getApplies() {
        return applies;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setApplies(String applies) {
        this.applies = applies;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
