package com.saybiu.bbs.response;


import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author wangfei
 */
//实体类属性为null不返回
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T>{

    private boolean success;
    private T data;
    private String responseCode;
    private String responseMessage;
    private String errorMessage;

    public ApiResult(boolean success, T data, String responseCode, String responseMessage, String errorMessage) {
        this.success = success;
        this.data = data;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.errorMessage = errorMessage;
    }

    public ApiResult(CommonCode commonCode) {
        this.success= commonCode.isSuccess();
        this.responseCode=commonCode.getResponseCode();
        this.responseMessage=commonCode.getResponseMessage();
    }
    public ApiResult(CommonCode commonCode,String errorMessage)
    {
        this.success= commonCode.isSuccess();
        this.responseCode=commonCode.getResponseCode();
        this.responseMessage=commonCode.getResponseMessage();
        this.errorMessage=errorMessage;
    }

    public ApiResult(CommonCode commonCode, T data) {
        this.success= commonCode.isSuccess();
        this.responseCode=commonCode.getResponseCode();
        this.responseMessage=commonCode.getResponseMessage();
        this.data = data;
    }
    public static ApiResult SUCCESS() {
        return new ApiResult<>(CommonCode.SUCCESS);
    }

    public static <T> ApiResult<T> SUCCESS(T data) {
        return new ApiResult<>(CommonCode.SUCCESS,data);
    }
    public static ApiResult FAIL() {
        return new ApiResult<>(CommonCode.FAIL);
    }

    public static <T> ApiResult<T> FAIL(T data) {
        return new ApiResult<>(CommonCode.FAIL, data);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "success=" + success +
                ", data=" + data +
                ", responseCode='" + responseCode + '\'' +
                ", responseMessage='" + responseMessage + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
