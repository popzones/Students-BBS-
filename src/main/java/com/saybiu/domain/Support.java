package com.saybiu.domain;

import java.io.Serializable;

public class Support implements Serializable {
    private Integer supportId;
    private Integer userSupportId;
    private String userSupportNickname;
    private Integer userSupportedId;
    private String userSupportedNickname;

    public Support() {
    }

    public Support(Integer supportId, Integer userSupportId, String userSupportNickname, Integer userSupportedId, String userSupportedNickname) {
        this.supportId = supportId;
        this.userSupportId = userSupportId;
        this.userSupportNickname = userSupportNickname;
        this.userSupportedId = userSupportedId;
        this.userSupportedNickname = userSupportedNickname;
    }

    public Integer getSupportId() {
        return supportId;
    }

    public void setSupportId(Integer supportId) {
        this.supportId = supportId;
    }

    public Integer getUserSupportId() {
        return userSupportId;
    }

    public void setUserSupportId(Integer userSupportId) {
        this.userSupportId = userSupportId;
    }

    public String getUserSupportNickname() {
        return userSupportNickname;
    }

    public void setUserSupportNickname(String userSupportNickname) {
        this.userSupportNickname = userSupportNickname;
    }

    public Integer getUserSupportedId() {
        return userSupportedId;
    }

    public void setUserSupportedId(Integer userSupportedId) {
        this.userSupportedId = userSupportedId;
    }

    public String getUserSupportedNickname() {
        return userSupportedNickname;
    }

    public void setUserSupportedNickname(String userSupportedNickname) {
        this.userSupportedNickname = userSupportedNickname;
    }

    @Override
    public String toString() {
        return "Supports{" +
                "supportId=" + supportId +
                ", userSupportId=" + userSupportId +
                ", userSupportNickname='" + userSupportNickname + '\'' +
                ", userSupportedId=" + userSupportedId +
                ", userSupportedNickname='" + userSupportedNickname + '\'' +
                '}';
    }
}
