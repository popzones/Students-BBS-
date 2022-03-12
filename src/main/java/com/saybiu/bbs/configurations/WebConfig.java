package com.saybiu.bbs.configurations;

import com.saybiu.bbs.interceptor.FileUploadInterceptor;
import com.saybiu.bbs.interceptor.HttpInterceptor;
import com.saybiu.bbs.resolver.MultipleJsonObjectResolver;
import com.saybiu.bbs.resolver.ParseTokenHandlerMethodArgumentResolver;
import com.saybiu.bbs.security.filter.JWTAuthorizationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.List;

/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/5/2 10:19 下午 （日期和时间）
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpInterceptor());
        registry.addInterceptor(new FileUploadInterceptor());
    }

    /**
     * 添加参数解析器
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new ParseTokenHandlerMethodArgumentResolver());
        resolvers.add(new MultipleJsonObjectResolver());
    }


}
