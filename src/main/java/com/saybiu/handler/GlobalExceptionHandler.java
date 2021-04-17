package com.saybiu.handler;

import com.saybiu.exception.ServiceException;
import com.saybiu.response.ApiResult;
import com.saybiu.response.CommonCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 形参为抛出异常的类型
     * @param e  抛出异常的类型
     * @return   返回ApiResult对象
     * ExceptionHandler应为所抛出的异常类
     */


    /**
     *
     * @param e
     * @return
     * 统一处理ServiceException
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ApiResult serviceException(ServiceException e)
    {
        return new ApiResult(e.getCommonCode());
    }
    /**
     *
     * @param e
     * @return
     * 处理全局RuntimeException
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ApiResult runtimeException(RuntimeException e)
    {
        e.printStackTrace();
        return new ApiResult(CommonCode.SERVER_ERROR);
    }

    /**
     *
     * @param e
     * @return
     * 若控制器使用了@RequestParam(require=true)，但客户端没有传入此必传参数
     * 则会抛出异常MissingServletRequestParameterException
     * 此方法集中处理非法参数
     * 提示{"success":false,"data":"有必传参数未传入","responseCode":"100001","responseMessage":"参数不完整或不正确"}
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ApiResult invalidParam(Exception e)
    {
        e.printStackTrace();
        return new ApiResult(CommonCode.INVALID_PARAM,"有必传参数未传入");
    }

    /**
     *
     * @param e
     * @return
     *
     * 若传入的参数类型不一致，则会提示
     * {"success":false,"data":"传入参数类型错误","responseCode":"100001","responseMessage":"参数不完整或不正确"}
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ApiResult IllegalArgumentException(Exception e)
    {
        return new ApiResult(CommonCode.INVALID_PARAM,"传入参数类型错误");
    }
}
