package com.saybiu.domain;

import java.io.Serializable;

public class Like implements Serializable {
    private Integer likeId;
    private Integer userId;
    private Integer userLikePostId;
    private Integer userLikeCommentId;
    private String userLikePostUrl;
    private String userLikePostTitle;
    private String userLikeCommentContent;
    private Integer userBelikedId;

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserLikePostId() {
        return userLikePostId;
    }

    public void setUserLikePostId(Integer userLikePostId) {
        this.userLikePostId = userLikePostId;
    }

    public Integer getUserLikeCommentId() {
        return userLikeCommentId;
    }

    public void setUserLikeCommentId(Integer userLikeCommentId) {
        this.userLikeCommentId = userLikeCommentId;
    }

    public String getUserLikePostUrl() {
        return userLikePostUrl;
    }

    public void setUserLikePostUrl(String userLikePostUrl) {
        this.userLikePostUrl = userLikePostUrl;
    }

    public String getUserLikePostTitle() {
        return userLikePostTitle;
    }

    public void setUserLikePostTitle(String userLikePostTitle) {
        this.userLikePostTitle = userLikePostTitle;
    }

    public String getUserLikeCommentContent() {
        return userLikeCommentContent;
    }

    public void setUserLikeCommentContent(String userLikeCommentContent) {
        this.userLikeCommentContent = userLikeCommentContent;
    }

    public Integer getUserBelikedId() {
        return userBelikedId;
    }

    public void setUserBelikedId(Integer userBelikedId) {
        this.userBelikedId = userBelikedId;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "likeId=" + likeId +
                ", userId=" + userId +
                ", userLikePostId=" + userLikePostId +
                ", userLikeCommentId=" + userLikeCommentId +
                ", userLikePostUrl='" + userLikePostUrl + '\'' +
                ", userLikePostTitle='" + userLikePostTitle + '\'' +
                ", userLikeCommentContent='" + userLikeCommentContent + '\'' +
                ", userBelikedId=" + userBelikedId +
                '}';
    }
}
