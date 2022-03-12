package com.saybiu.bbs.security.config;

import com.alibaba.fastjson.JSON;
import com.saybiu.bbs.exception.ServiceException;
import com.saybiu.bbs.response.CommonCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/5/12 1:08 上午 （日期和时间）
 */

/**
 * 用户尚未登录或登录失效
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Resource
    HandlerExceptionResolver handlerExceptionResolver;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //抛出用户尚未登录异常
        handlerExceptionResolver.resolveException(request,response,null,new ServiceException(CommonCode.NO_LOGIN));
    }
}
