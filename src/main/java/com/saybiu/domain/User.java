package com.saybiu.domain;

import java.io.Serializable;

public class User implements Serializable {
    private Integer userId;
    private String userPhone;
    private String userPassword;
    private boolean userMuted;
    private String userCreateTime;
    private String userUpdateTime;
    private String userRegisterIp;

    public User() {
    }

    public User(Integer userId, String userPhone, String userPassword, boolean userMuted, String userCreateTime, String userUpdateTime, String userRegisterIp) {
        this.userId = userId;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
        this.userMuted = userMuted;
        this.userCreateTime = userCreateTime;
        this.userUpdateTime = userUpdateTime;
        this.userRegisterIp = userRegisterIp;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isUserMuted() {
        return userMuted;
    }

    public void setUserMuted(boolean userMuted) {
        this.userMuted = userMuted;
    }

    public String getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(String userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public String getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(String userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    public String getUserRegisterIp() {
        return userRegisterIp;
    }

    public void setUserRegisterIp(String userRegisterIp) {
        this.userRegisterIp = userRegisterIp;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userPhone='" + userPhone + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userMuted=" + userMuted +
                ", userCreateTime='" + userCreateTime + '\'' +
                ", userUpdateTime='" + userUpdateTime + '\'' +
                ", userRegisterIp='" + userRegisterIp + '\'' +
                '}';
    }
}
