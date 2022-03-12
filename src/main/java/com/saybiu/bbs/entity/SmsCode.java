package com.saybiu.bbs.entity;

/**
 * @Author wangwei
 * @Date 2021/5/21 10:23
 * @Version 1.0
 */
public class SmsCode {
    private String date;
    private String dynamicCode;
    private String dynamicCodeType;

    public SmsCode() {
    }

    public SmsCode(String date, String dynamicCode, String dynamicCodeType) {
        this.date = date;
        this.dynamicCode = dynamicCode;
        this.dynamicCodeType = dynamicCodeType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDynamicCode() {
        return dynamicCode;
    }

    public void setDynamicCode(String dynamicCode) {
        this.dynamicCode = dynamicCode;
    }

    public String getDynamicCodeType() {
        return dynamicCodeType;
    }

    public void setDynamicCodeType(String dynamicCodeType) {
        this.dynamicCodeType = dynamicCodeType;
    }
}
