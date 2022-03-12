package com.saybiu.bbs.configurations;


import com.saybiu.bbs.security.SecurityProperties;
import com.saybiu.bbs.security.config.RestAuthenticationEntryPoint;
import com.saybiu.bbs.security.evaluator.CustomPermissionEvaluator;
import com.saybiu.bbs.security.filter.JWTAuthorizationFilter;
import com.saybiu.bbs.security.filter.SmsCodeAuthenticationFilter;
import com.saybiu.bbs.security.filter.UserPasswordAuthenticationFilter;
import com.saybiu.bbs.security.handler.CustomAuthenticationFailureHandler;
import com.saybiu.bbs.security.handler.CustomAuthenticationSuccessHandler;
import com.saybiu.bbs.security.config.SmsCodeAuthenticationSecurityConfig;
import com.saybiu.bbs.security.config.UserPasswordAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.Resource;

/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/5/11 4:20 下午 （日期和时间）
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行全部静态资源
        web.ignoring().antMatchers("/static/**");
    }
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private UserPasswordAuthenticationSecurityConfig userPasswordAuthenticationSecurityConfig;
    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 登录成功处理
     */
    @Resource
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    /**
     * 自定义未登录处理
     */
    @Resource
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Resource
    CustomAuthenticationFailureHandler authenticationFailure;
    @Resource
    AuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    AccessDeniedHandler authorizationFailHandler;
    @Resource
    HandlerExceptionResolver handlerExceptionResolver;





    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //注册认证处理器
        auth.authenticationProvider(daoAuthenticationProvider());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                        http.apply(smsCodeAuthenticationSecurityConfig);
        http.apply(userPasswordAuthenticationSecurityConfig);
                http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and().authorizeRequests()
                        .antMatchers(securityProperties.getOpenApi()).permitAll()
                        .anyRequest().authenticated()
                        .and().exceptionHandling()
                        .accessDeniedHandler(authorizationFailHandler)
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .and()
                        .formLogin().loginProcessingUrl("/login")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureHandler(authenticationFailure)
                        .and().csrf().disable();


        http.addFilterBefore(new JWTAuthorizationFilter(handlerExceptionResolver,securityProperties), UsernamePasswordAuthenticationFilter.class);
    }


    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return handler;
    }


}
