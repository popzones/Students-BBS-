package com.saybiu.controller;

import com.alibaba.fastjson.JSONObject;
import com.saybiu.domain.User;
import com.saybiu.exception.ServiceException;
import com.saybiu.response.ApiResult;
import com.saybiu.response.CommonCode;
import com.saybiu.utils.JwtUtil;
import io.jsonwebtoken.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/4/17 11:53 上午 （日期和时间）
 */
@Controller

public class TestController {
    @RequestMapping(value = "test11")
    @ResponseBody

    public void test11(@RequestHeader(name = "Authorization") String Authorization)
    {

        System.out.println(JwtUtil.getUserId(Authorization));
    }
    @RequestMapping("user/login")
    @ResponseBody
    public Map login()
    {
        User user=new User();
        user.setUserId(123);
        user.setUserPhone("123");
        Map map=new HashMap();
        map.put("token",JwtUtil.creatToken(user));
        map.put("userId",user.getUserId());
        return map;
    }
    @RequestMapping("aaa")
    @ResponseBody
    public void test(@RequestHeader String Authorization)
    {
        int userId = JwtUtil.getUserId(Authorization);
        System.out.println(userId);
    }
}
