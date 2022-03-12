package com.saybiu.bbs.resolver;

import com.saybiu.bbs.utils.JwtUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/4/18 12:15 下午 （日期和时间）
 */

@Configuration
public class ParseTokenHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if("userId".equals(methodParameter.getParameterName()))
        {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {
        HttpServletRequest servletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String authorization = servletRequest.getHeader("Authorization");
        if(authorization!=null&&!"Bearer null".equals(authorization))
        {
            String token= JwtUtil.splitToken(authorization);
            int userId = JwtUtil.getUserId(token);
            return userId;
        }
        return null;
    }
}
