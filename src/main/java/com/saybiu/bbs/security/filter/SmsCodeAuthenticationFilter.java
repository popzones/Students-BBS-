package com.saybiu.bbs.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.saybiu.bbs.security.token.SmsCodeAuthenticationToken;
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
 * 短信登录的鉴权过滤器，模仿 UsernamePasswordAuthenticationFilter 实现
 * @author jitwxs
 * @since 2019/1/9 13:52
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    /**
     * form表单中手机号码的字段name
     */
    public static final String SPRING_SECURITY_FORM_MOBILE_KEY = "mobile";

    private String mobileParameter = SPRING_SECURITY_FORM_MOBILE_KEY;
    /**
     * 是否仅 POST 方式
     */
    private boolean postOnly = true;

    public SmsCodeAuthenticationFilter() {
        // 短信登录的请求 post 方式的 /login
        super(new AntPathRequestMatcher("/sms/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        Map<String,Object> paramMap=getParamMap(request);
        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(paramMap);
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
        } catch (Exception e)
        {
            throw new ParseParamError("参数解析错误");
        }
    }
    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }


}
