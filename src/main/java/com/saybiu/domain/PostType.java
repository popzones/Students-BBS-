package com.saybiu.domain;

import java.io.Serializable;

public class PostType implements Serializable {
    private Integer postTypeId;
    private String postTypeName;
    private boolean postTypeMuted;

    public PostType() {
    }

    public PostType(Integer postTypeId, String postTypeName, boolean postTypeMuted) {
        this.postTypeId = postTypeId;
        this.postTypeName = postTypeName;
        this.postTypeMuted = postTypeMuted;
    }

    public Integer getPostTypeId() {
        return postTypeId;
    }

    public void setPostTypeId(Integer postTypeId) {
        this.postTypeId = postTypeId;
    }

    public String getPostTypeName() {
        return postTypeName;
    }

    public void setPostTypeName(String postTypeName) {
        this.postTypeName = postTypeName;
    }

    public boolean isPostTypeMuted() {
        return postTypeMuted;
    }

    public void setPostTypeMuted(boolean postTypeMuted) {
        this.postTypeMuted = postTypeMuted;
    }

    @Override
    public String toString() {
        return "PostType{" +
                "postTypeId=" + postTypeId +
                ", postTypeName='" + postTypeName + '\'' +
                ", postTypeMuted=" + postTypeMuted +
                '}';
    }
}
