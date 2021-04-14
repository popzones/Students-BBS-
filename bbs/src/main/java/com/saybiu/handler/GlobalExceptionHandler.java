package com.saybiu.handler;

import com.saybiu.exception.ServiceException;
import com.saybiu.response.ApiResult;
import com.saybiu.response.CommonCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ApiResult serviceException(ServiceException e)
    {
        return new ApiResult(e.getCommonCode());
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResult runtimeException(Exception e)
    {
        e.printStackTrace();
        return new ApiResult(CommonCode.SERVER_ERROR);
    }


}
