package com.saybiu.bbs.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saybiu.bbs.exception.ServiceException;
import com.saybiu.bbs.response.CommonCode;
import com.saybiu.bbs.security.exception.ParseParamError;
import com.saybiu.bbs.security.exception.PasswordNotFoundError;
import com.saybiu.bbs.security.exception.SmsCodeError;
import com.saybiu.bbs.security.exception.SmsCodeNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Resource
    private HandlerExceptionResolver handlerExceptionResolver;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof UsernameNotFoundException){
            handlerExceptionResolver.resolveException(request,response,null,new ServiceException(CommonCode.FAIL,"用户不存在"));
        }
        if(exception instanceof LockedException){
            handlerExceptionResolver.resolveException(request,response,null,new ServiceException(CommonCode.FAIL,"账号已被封禁,请联系管理员"));
        }
        if(exception instanceof BadCredentialsException){
            handlerExceptionResolver.resolveException(request,response,null,new ServiceException(CommonCode.FAIL,"密码错误"));
        }
        if(exception instanceof DisabledException){
            handlerExceptionResolver.resolveException(request,response,null,new ServiceException(CommonCode.FAIL,"账户未激活"));
        }
        if(exception instanceof SmsCodeError){
            handlerExceptionResolver.resolveException(request,response,null,new ServiceException(CommonCode.FAIL,"验证码错误"));
        }
        if(exception instanceof SmsCodeNotFound){
            handlerExceptionResolver.resolveException(request,response,null,new ServiceException(CommonCode.FAIL,"请先发送验证码"));
        }
        if(exception instanceof ParseParamError){
            handlerExceptionResolver.resolveException(request,response,null,new ServiceException(CommonCode.SERVER_ERROR,"参数解析错误"));
        }
        if(exception instanceof PasswordNotFoundError){
            handlerExceptionResolver.resolveException(request,response,null,new ServiceException(CommonCode.FAIL,"您还未设置密码"));
        }
        return;
    }
}