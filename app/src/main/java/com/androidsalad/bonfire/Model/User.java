package com.androidsalad.bonfire.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    private String userId;
    private String userName;
    private String userImagePath;
    private String userThumbPath;

    public User() {
    }

    public User(String userId, String userName, String userImagePath, String userThumbPath) {
        this.userId = userId;
        this.userName = userName;
        this.userImagePath = userImagePath;
        this.userThumbPath = userThumbPath;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImagePath() {
        return userImagePath;
    }

    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }

    public String getUserThumbPath() {
        return userThumbPath;
    }

    public void setUserThumbPath(String userThumbPath) {
        this.userThumbPath = userThumbPath;
    }
}
