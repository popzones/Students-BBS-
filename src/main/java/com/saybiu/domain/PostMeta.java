package com.saybiu.domain;

import java.io.Serializable;

public class PostMeta implements Serializable {
    private Integer postmetaId;
    private String postLatestUpdateTime;
    private String postContent;
    private Integer postId;
    private String userProfilePictureUrl;

    public PostMeta() {
    }

    public PostMeta(Integer postmetaId, String postLatestUpdateTime, String postContent, Integer postId, String userProfilePictureUrl) {
        this.postmetaId = postmetaId;
        this.postLatestUpdateTime = postLatestUpdateTime;
        this.postContent = postContent;
        this.postId = postId;
        this.userProfilePictureUrl = userProfilePictureUrl;
    }

    public Integer getPostmetaId() {
        return postmetaId;
    }

    public void setPostmetaId(Integer postmetaId) {
        this.postmetaId = postmetaId;
    }

    public String getPostLatestUpdateTime() {
        return postLatestUpdateTime;
    }

    public void setPostLatestUpdateTime(String postLatestUpdateTime) {
        this.postLatestUpdateTime = postLatestUpdateTime;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getUserProfilePictureUrl() {
        return userProfilePictureUrl;
    }

    public void setUserProfilePictureUrl(String userProfilePictureUrl) {
        this.userProfilePictureUrl = userProfilePictureUrl;
    }

    @Override
    public String toString() {
        return "PostMeta{" +
                "postmetaId=" + postmetaId +
                ", postLatestUpdateTime='" + postLatestUpdateTime + '\'' +
                ", postContent='" + postContent + '\'' +
                ", postId=" + postId +
                ", userProfilePictureUrl='" + userProfilePictureUrl + '\'' +
                '}';
    }
}
