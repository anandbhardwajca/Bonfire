package com.androidsalad.bonfire.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Post {

    private String postId;
    private String postText;
    private String postUserId;
    private String postUserName;
    private String postDate;

    public Post() {
    }

    public Post(String postId, String postText, String postUserId, String postUserName, String postDate) {
        this.postId = postId;
        this.postText = postText;
        this.postUserId = postUserId;
        this.postUserName = postUserName;
        this.postDate = postDate;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostUserId() {
        return postUserId;
    }

    public void setPostUserId(String postUserId) {
        this.postUserId = postUserId;
    }

    public String getPostUserName() {
        return postUserName;
    }

    public void setPostUserName(String postUserName) {
        this.postUserName = postUserName;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
