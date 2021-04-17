package com.saybiu.response;


public class ApiResult<T> {

    private boolean success;
    private T data;
    private String responseCode;
    private String responseMessage;

    public ApiResult(CommonCode commonCode) {
        this.success= commonCode.isSuccess();
        this.responseCode=commonCode.getResponseCode();
        this.responseMessage=commonCode.getResponseMessage();
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
}
