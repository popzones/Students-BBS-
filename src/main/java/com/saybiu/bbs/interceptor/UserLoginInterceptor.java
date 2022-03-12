package com.saybiu.bbs.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.saybiu.bbs.response.ApiResult;
import com.saybiu.bbs.response.CommonCode;
import com.saybiu.bbs.utils.JedisUtils;
import com.saybiu.bbs.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 自定义token拦截器
 * @author wangfei
 */

public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");
        //System.out.println("======================================"+token);
        //token存在
        if (token!=null&&!"Bearer null".equals(token)) {
            //验证token是否正确
            //在redis中拿到token
            token= JwtUtil.splitToken(token);
            Jedis jedis = JedisUtils.getJedis();
            //解析token获取用户id并在redis中查询
            String userId = String.valueOf(JwtUtil.getUserId(token));
            String fromRedisToken = jedis.get(userId);
            //System.out.println("-----------------------------------"+fromRedisToken);
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
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.write(JSONObject.toJSONString(new ApiResult(CommonCode.LOGIN_OUT_TIME,"登陆已超时")));
            writer.flush();
            writer.close();
            return false;
        }
        response.setStatus(401);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(JSONObject.toJSONString(new ApiResult(CommonCode.NO_LOGIN,"用户尚未登陆")));
        writer.flush();
        writer.close();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
