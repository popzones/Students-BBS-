package com.saybiu.bbs.security.config;

import com.saybiu.bbs.security.filter.UserPasswordAuthenticationFilter;
import com.saybiu.bbs.security.handler.CustomAuthenticationFailureHandler;
import com.saybiu.bbs.security.handler.CustomAuthenticationSuccessHandler;
import com.saybiu.bbs.security.provide.UserPasswordAuthenticationProvide;
import com.saybiu.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author wangwei
 * @Date 2021/5/27 19:42
 * @Version 1.0
 */
@Component
public class UserPasswordAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Resource
    private UserService userService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        UserPasswordAuthenticationFilter userPasswordAuthenticationFilter = new UserPasswordAuthenticationFilter();
        userPasswordAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        userPasswordAuthenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        userPasswordAuthenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);

        UserPasswordAuthenticationProvide userPasswordAuthenticationProvide = new UserPasswordAuthenticationProvide();
        userPasswordAuthenticationProvide.setUserService(userService);

        http.authenticationProvider(userPasswordAuthenticationProvide)
                .addFilterAfter(userPasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
