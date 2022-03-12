package com.saybiu.bbs.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.saybiu.bbs.security.token.UserPasswordAuthenticationToken;
import com.saybiu.bbs.security.exception.ParseParamError;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

/**
 * @Author wangwei
 * @Date 2021/5/27 19:29
 * @Version 1.0
 */
public class UserPasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


//    @Resource
//    HandlerExceptionResolver handlerExceptionResolver;
    /**
     * 是否仅 POST 方式
     */
    private boolean postOnly = true;
    public UserPasswordAuthenticationFilter() {
        // 密码登录的请求 post 方式的 /login
        super(new AntPathRequestMatcher("/common/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        Map<String,Object> paramMap=getParamMap(request);
        UserPasswordAuthenticationToken authRequest = new UserPasswordAuthenticationToken(paramMap);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected Map<String, Object> getParamMap(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null)
        {
            buffer.append(line);
        }
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(buffer.toString());
            return jsonObject.getInnerMap();
        }
        catch (Exception e)
        {
            throw new ParseParamError("参数解析错误");
        }
    }
    protected void setDetails(HttpServletRequest request, UserPasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }


}
