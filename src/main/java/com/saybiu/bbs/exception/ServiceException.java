package com.saybiu.bbs.exception;


import com.saybiu.bbs.response.CommonCode;

public class ServiceException extends RuntimeException{
    CommonCode commonCode;
    String errorMessage;
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(CommonCode commonCode,String errorMessage)
    {
        this.commonCode=commonCode;
        this.errorMessage=errorMessage;
    }
    public ServiceException(CommonCode commonCode)
    {
        this.commonCode=commonCode;
    }
    public CommonCode getCommonCode() {
        return commonCode;
    }
    public String getErrorMessage()
    {
        return errorMessage;
    }
}
