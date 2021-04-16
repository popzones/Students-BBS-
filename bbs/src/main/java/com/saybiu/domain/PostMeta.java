package com.saybiu.domain;

import java.io.Serializable;

public class PostMeta implements Serializable {
    private Integer postmetaId;
    private String postLatestUpdateTime;
    private String postContent;
    private Integer userId;
    private String userProfilePictureUrl;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserProfilePictureUrl() {
        return userProfilePictureUrl;
    }

    public void setUserProfilePictureUrl(String userProfilePictureUrl) {
        this.userProfilePictureUrl = userProfilePictureUrl;
    }

    @Override
    public String toString() {
        return "Postmeta{" +
                "postmetaId=" + postmetaId +
                ", postLatestUpdateTime='" + postLatestUpdateTime + '\'' +
                ", postContent='" + postContent + '\'' +
                ", userId=" + userId +
                ", userProfilePictureUrl='" + userProfilePictureUrl + '\'' +
                '}';
    }
}
