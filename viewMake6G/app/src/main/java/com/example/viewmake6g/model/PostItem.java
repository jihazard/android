package com.example.viewmake6g.model;

public class PostItem {

    private boolean isUserLike;
    private int postLikeCount;
    private String userName;
    private String postImgUrl;
    private String postText;

    public PostItem(boolean isUserLike, int postLikeCount, String userName, String postImgUrl, String postText) {
        this.isUserLike = isUserLike;
        this.postLikeCount = postLikeCount;
        this.userName = userName;
        this.postImgUrl = postImgUrl;
        this.postText = postText;
    }

    public boolean isUserLike() {
        return isUserLike;
    }

    public void setUserLike(boolean userLike) {
        isUserLike = userLike;
    }

    public int getPostLikeCount() {
        return postLikeCount;
    }

    public void setPostLikeCount(int postLikeCount) {
        this.postLikeCount = postLikeCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostImgUrl() {
        return postImgUrl;
    }

    public void setPostImgUrl(String postImgUrl) {
        this.postImgUrl = postImgUrl;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    @Override
    public String toString() {
        return "PostItem{" +
                "isUserLike=" + isUserLike +
                ", postLikeCount=" + postLikeCount +
                ", userName='" + userName + '\'' +
                ", postImgUrl='" + postImgUrl + '\'' +
                ", postText='" + postText + '\'' +
                '}';
    }
}
