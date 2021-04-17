package com.saybiu.exception;

import com.saybiu.response.CommonCode;

public class ServiceException extends RuntimeException{
    CommonCode commonCode;
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(CommonCode commonCode)
    {
        this.commonCode=commonCode;
    }

    public CommonCode getCommonCode() {
        return commonCode;
    }
}
