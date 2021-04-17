package com.saybiu.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.saybiu.exception.ServiceException;
import com.saybiu.response.ApiResult;
import com.saybiu.response.CommonCode;
import com.saybiu.utils.JedisUtils;
import com.saybiu.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 自定义token拦截器
 * @author wangfei
 */
public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        //获取前端传递的token
        String token = request.getHeader("Authorization");
        //token存在
        if (null != token) {
            //验证token是否正确
            //在redis中拿到token
            Jedis jedis = JedisUtils.getJedis();
            //解析token获取用户id并在redis中查询
            String userId = String.valueOf(JwtUtil.getUserId(token));
            String fromRedisToken = jedis.get(userId);
            //redis中查询到
            if (fromRedisToken!=null){
                //解析
                boolean result = JwtUtil.verify(fromRedisToken);
                //续期半个小时
                jedis.expire(userId,1800);
                if (result) {
                    return true;
                }
            }
            response.setStatus(401);
            throw new ServiceException(CommonCode.NO_LOGIN);
        }
        response.setStatus(401);
       throw new ServiceException(CommonCode.LOGIN_OUT_TIME);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
