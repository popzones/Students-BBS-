package com.saybiu.bbs.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.saybiu.bbs.entity.User;
import com.saybiu.bbs.exception.ServiceException;
import com.saybiu.bbs.response.CommonCode;
import com.saybiu.bbs.security.SecurityProperties;
import com.saybiu.bbs.security.token.UserPasswordAuthenticationToken;
import com.saybiu.bbs.utils.JwtUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/5/30 5:19 下午 （日期和时间）
 */

public class JWTAuthorizationFilter extends OncePerRequestFilter {

  private HandlerExceptionResolver handlerExceptionResolverl;
  private SecurityProperties securityProperties;

    public JWTAuthorizationFilter(HandlerExceptionResolver handlerExceptionResolverl, SecurityProperties securityProperties) {
        this.handlerExceptionResolverl = handlerExceptionResolverl;
        this.securityProperties = securityProperties;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        /**
         * 放行公共api接口
         */
        if (isOpenApi(request)) {
            chain.doFilter(request,response);
            return;
        }

        SecurityContext context = SecurityContextHolder.getContext();
        String token = request.getHeader("Authorization");
        if (token!=null&&!"Bearer null".equals(token))
        {
            token= JwtUtil.splitToken(token);
            String userInfoFromRedis = JwtUtil.getUserInfoFromRedis(token);
            if(!userInfoFromRedis.equals("NOT_EXIST"))
            {
                JSONObject userInfo = JSONObject.parseObject(userInfoFromRedis);
                String redisToken = userInfo.getString("token");
                if(redisToken.equals(token))
                {
                    User user = userInfo.getObject("user", User.class);
                    List roles = getRole(userInfo.getObject("authorization", List.class));
                    UserPasswordAuthenticationToken userPasswordAuthenticationToken=new UserPasswordAuthenticationToken(user,roles);
                    context.setAuthentication(userPasswordAuthenticationToken);
                    chain.doFilter(request,response);
                    return;
                }
            }
        }
        handlerExceptionResolverl.resolveException(request,response,null,new ServiceException(CommonCode.NO_LOGIN,"用户未登录或登录已失效"));
    }


    public boolean isOpenApi(HttpServletRequest request)
    {
        String uri = request.getRequestURI();
        String[] openApi = securityProperties.getOpenApi();
        for (String s : openApi) {
            if(s.contains(uri))
            {
                return true;
            }
        }
        return false;
    }
    public List getRole(List list)
    {
        StringBuffer stringBuffer = new StringBuffer();
        for (Object o : list) {
            JSONObject jsonObject = JSONObject.parseObject(o.toString());
            String authority = jsonObject.getString("authority");
            stringBuffer.append(","+authority);
        }
        List<GrantedAuthority> authorityGranterList= AuthorityUtils.commaSeparatedStringToAuthorityList( stringBuffer.toString().substring(1));
        return authorityGranterList;
    }
}
