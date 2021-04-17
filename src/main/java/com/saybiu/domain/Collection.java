package com.saybiu.domain;

import java.io.Serializable;

public class Collection implements Serializable {
    private Integer collectionId;
    private Integer userId;
    private Integer postId;
    private String postUrl;
    private String collectionTime;
    private Integer collectionPostWriter;
    private String postTitle;

    public Collection() {
    }

    public Collection(Integer collectionId, Integer userId, Integer postId, String postUrl, String collectionTime, Integer collectionPostWriter, String postTitle) {
        this.collectionId = collectionId;
        this.userId = userId;
        this.postId = postId;
        this.postUrl = postUrl;
        this.collectionTime = collectionTime;
        this.collectionPostWriter = collectionPostWriter;
        this.postTitle = postTitle;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }

    public Integer getCollectionPostWriter() {
        return collectionPostWriter;
    }

    public void setCollectionPostWriter(Integer collectionPostWriter) {
        this.collectionPostWriter = collectionPostWriter;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    @Override
    public String toString() {
        return "Collections{" +
                "collectionId=" + collectionId +
                ", userId=" + userId +
                ", postId=" + postId +
                ", postUrl='" + postUrl + '\'' +
                ", collectionTime='" + collectionTime + '\'' +
                ", collectionPostWriter=" + collectionPostWriter +
                ", postTitle='" + postTitle + '\'' +
                '}';
    }
}
