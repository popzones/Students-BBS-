package com.saybiu.bbs.security.handler;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saybiu.bbs.entity.User;
import com.saybiu.bbs.response.ApiResult;
import com.saybiu.bbs.response.CommonCode;
import com.saybiu.bbs.utils.JedisUtils;
import com.saybiu.bbs.utils.JwtUtil;
import com.saybiu.bbs.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        WebAuthenticationDetails details1=(WebAuthenticationDetails)authentication.getDetails();
        Map infoMap = saveInfoToRedis(user, authorities, details1.getRemoteAddress());
        String token = JwtUtil.creatToken(user);
        setTokenAndTime(infoMap,token);
        JwtUtil.userInfoToRedis(JSONObject.toJSONString(infoMap),user.getUserId());
        System.out.println(JSONObject.toJSONString(infoMap));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSONObject.toJSONString(new ApiResult(CommonCode.SUCCESS,infoMap)));
    }
    public Map saveInfoToRedis(User user, List<GrantedAuthority> grantedAuthorities,String ip)
    {
        user.setUserPassword(null);
        Map infoMap=new HashMap();
        infoMap.put("user",user);
        infoMap.put("authorization",grantedAuthorities);
        infoMap.put("currentIP",ip);
        infoMap.put("token",null);

        return infoMap;
    }
    public void setTokenAndTime(Map map,String token)
    {
        map.put("token",token);
        map.put("date",Utils.toDate(new Date()));
    }
}