package com.saybiu.bbs.security.handler;

import com.saybiu.bbs.exception.ServiceException;
import com.saybiu.bbs.response.CommonCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author wangwei
 * @Date 2021/5/18 18:03
 * @Version 1.0
 */

/**
 * 没有权限访问处理器
 */
@Component
public class AuthorizationFailHandler implements AccessDeniedHandler {

    @Resource
    HandlerExceptionResolver handlerExceptionResolver;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
       handlerExceptionResolver.resolveException(httpServletRequest,httpServletResponse,null,new ServiceException(CommonCode.NO_AUTH,"用户权限不足,禁止访问"));
    }
}
