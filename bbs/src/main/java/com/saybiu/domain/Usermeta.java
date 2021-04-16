package com.saybiu.domain;

import java.io.Serializable;

public class Usermeta implements Serializable {
    private Integer usermetaId;
    private Integer userId;
    private String userSex;
    private String userPictureUrl;
    private Integer userSupportedNum;
    private Integer userSupportPostNum;
    private Integer userPostNum;
    private Integer commentRepliedId;
    private String userLatestIp;
    private String userNickName;
    private Integer userSupportNum;
    private Integer userBelikedNum;
    private Integer userLikeNum;

    public Integer getUsermetaId() {
        return usermetaId;
    }

    public void setUsermetaId(Integer usermetaId) {
        this.usermetaId = usermetaId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserPictureUrl() {
        return userPictureUrl;
    }

    public void setUserPictureUrl(String userPictureUrl) {
        this.userPictureUrl = userPictureUrl;
    }

    public Integer getUserSupportedNum() {
        return userSupportedNum;
    }

    public void setUserSupportedNum(Integer userSupportedNum) {
        this.userSupportedNum = userSupportedNum;
    }

    public Integer getUserSupportPostNum() {
        return userSupportPostNum;
    }

    public void setUserSupportPostNum(Integer userSupportPostNum) {
        this.userSupportPostNum = userSupportPostNum;
    }

    public Integer getUserPostNum() {
        return userPostNum;
    }

    public void setUserPostNum(Integer userPostNum) {
        this.userPostNum = userPostNum;
    }

    public Integer getCommentRepliedId() {
        return commentRepliedId;
    }

    public void setCommentRepliedId(Integer commentRepliedId) {
        this.commentRepliedId = commentRepliedId;
    }

    public String getUserLatestIp() {
        return userLatestIp;
    }

    public void setUserLatestIp(String userLatestIp) {
        this.userLatestIp = userLatestIp;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public Integer getUserSupportNum() {
        return userSupportNum;
    }

    public void setUserSupportNum(Integer userSupportNum) {
        this.userSupportNum = userSupportNum;
    }

    public Integer getUserBelikedNum() {
        return userBelikedNum;
    }

    public void setUserBelikedNum(Integer userBelikedNum) {
        this.userBelikedNum = userBelikedNum;
    }

    public Integer getUserLikeNum() {
        return userLikeNum;
    }

    public void setUserLikeNum(Integer userLikeNum) {
        this.userLikeNum = userLikeNum;
    }

    @Override
    public String toString() {
        return "Usermeta{" +
                "usermetaId=" + usermetaId +
                ", userId=" + userId +
                ", userSex='" + userSex + '\'' +
                ", userPictureUrl='" + userPictureUrl + '\'' +
                ", userSupportedNum=" + userSupportedNum +
                ", userSupportPostNum=" + userSupportPostNum +
                ", userPostNum=" + userPostNum +
                ", commentRepliedId=" + commentRepliedId +
                ", userLatestIp='" + userLatestIp + '\'' +
                ", userNickName='" + userNickName + '\'' +
                ", userSupportNum=" + userSupportNum +
                ", userBelikedNum=" + userBelikedNum +
                ", userLikeNum=" + userLikeNum +
                '}';
    }
}
