package com.saybiu.domain;

import java.io.Serializable;

public class Picture implements Serializable {
    private Integer pictureId;
    private String pictureUrl;
    private Integer postId;
    private Integer commentId;

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pictureId=" + pictureId +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", postId=" + postId +
                ", commentId=" + commentId +
                '}';
    }
}
