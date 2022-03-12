package com.saybiu.bbs.response;

/**
 * @author wangfei
 */

public enum CommonCode {

    /**
     *      状态码设定标准：
     *      1.用户级别造成的错误，统一以100000为开头
     *      2.服务器级别造成的错误，统一以200000为开头
     *      3.对数据库的增删改查出现的错误统一以300000为开头
     */
    SUCCESS(true, "000000", "请求成功"),
    FAIL(false, "000001", "请求失败"),
    INVALID_PARAM(false, "100001", "参数不完整或不正确"),
    NO_LOGIN(false, "100002", "用户尚未登录"),
    NO_AUTH(false, "100003", "权限不足，禁止操作"),
    LOGIN_OUT_TIME(false, "100004", "用户登录已失效"),
    LOGIN_SUCCESS(true, "100005", "用户登录成功"),
    MAX_UPLOAD_ERROR(false, "100006", "上传的文件大小超过限制"),
    EMPTY_FILE(false, "100007", "文件大小为0"),
    SERVER_ERROR(false, "200000", "系统繁忙,请刷新或联系管理员处理"),
    NOT_FOUND(false, "200001", "请求资源的地址不存在或有误，请核对后重新请求"),
    INSERT_ERROR(false, "300000", "插入数据失败"),
    UPDATE_ERROR(false, "300001", "更新数据失败"),
    DELETE_ERROR(false, "300002", "删除数据失败"),
    SELECT_ERROR(false, "300003", "未查询到相关数据"),
    INSERT_REPEAT(false, "300004", "数据重复，插入失败");







    /**
     * 操作状态
     */
    boolean success;

    /**
     * 操作代码
     */
    String responseCode;

    /**
     * 提示信息
     */
    String responseMessage;


    public boolean isSuccess() {
        return success;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    CommonCode(boolean success, String responseCode, String responseMessage) {
        this.success = success;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
