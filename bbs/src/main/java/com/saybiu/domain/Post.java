package com.saybiu.domain;

import java.io.Serializable;

public class Post implements Serializable {
    private Integer postId;
    private String postTitle;
    private String postTime;
    private boolean postMuted;
    private String postTypeName;
    private Integer postReadNum;
    private Integer postLikeNum;
    private Integer postCollectionNum;
    private Integer postCommentedNum;
    private String userNickname;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public boolean isPostMuted() {
        return postMuted;
    }

    public void setPostMuted(boolean postMuted) {
        this.postMuted = postMuted;
    }

    public String getPostTypeName() {
        return postTypeName;
    }

    public void setPostTypeName(String postTypeName) {
        this.postTypeName = postTypeName;
    }

    public Integer getPostReadNum() {
        return postReadNum;
    }

    public void setPostReadNum(Integer postReadNum) {
        this.postReadNum = postReadNum;
    }

    public Integer getPostLikeNum() {
        return postLikeNum;
    }

    public void setPostLikeNum(Integer postLikeNum) {
        this.postLikeNum = postLikeNum;
    }

    public Integer getPostCollectionNum() {
        return postCollectionNum;
    }

    public void setPostCollectionNum(Integer postCollectionNum) {
        this.postCollectionNum = postCollectionNum;
    }

    public Integer getPostCommentedNum() {
        return postCommentedNum;
    }

    public void setPostCommentedNum(Integer postCommentedNum) {
        this.postCommentedNum = postCommentedNum;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "postId=" + postId +
                ", postTitle='" + postTitle + '\'' +
                ", postTime='" + postTime + '\'' +
                ", postMuted=" + postMuted +
                ", postTypeName='" + postTypeName + '\'' +
                ", postReadNum=" + postReadNum +
                ", postLikeNum=" + postLikeNum +
                ", postCollectionNum=" + postCollectionNum +
                ", postCommentedNum=" + postCommentedNum +
                ", userNickname='" + userNickname + '\'' +
                '}';
    }
}
