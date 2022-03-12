package com.saybiu.bbs.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saybiu.bbs.annotation.JsonObject;
import com.saybiu.bbs.entity.User;
import com.saybiu.bbs.entity.GroupA;
import com.saybiu.bbs.entity.GroupB;
import com.saybiu.bbs.exception.ServiceException;
import com.saybiu.bbs.response.ApiResult;
import com.saybiu.bbs.response.CommonCode;
import com.saybiu.bbs.service.UserService;

import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangwei
 * @Date 2021/5/7 13:00
 * @Version 1.0
 */
@RestController
@Validated
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    HttpServletRequest httpServletRequest;

    /**
     * 用户获取验证码
     * @param phone
     * @param actionType
     * @throws InterruptedException
     */
    @PutMapping("code")
    public void getCode(@JsonObject String phone,@JsonObject String actionType) throws InterruptedException {
        User user = new User();
        user.setUserPhone(phone);
        //获取验证码并存入redis
        userService.sendSmsAndToRedis(user,actionType);
    }

//    /**
//     * 用户注册(单注册)
//     * @param phone 电话号码
//     * @param code 验证码
//     *
//     */
//    @PostMapping("register")
//    public Map register(@JsonObject String phone,@JsonObject String code, HttpServletRequest request){
//        User user = new User();
//        user.setUserRegisterIp(request.getRemoteAddr());
//        user.setUserPhone(phone);
//        String ip = request.getRemoteAddr();
//        u = userService.commonRegister(user,ip);
//        return map;
//    }

    /**
     * 注册(自动登录)
     * @param phone
     * @param smsCode
     * @return
     */
    @PostMapping(value="/sms/register")
    public ApiResult register(@JsonObject String phone, @JsonObject String smsCode)
    {
        int localPort = httpServletRequest.getLocalPort();
        RestTemplate restTemplate=new RestTemplate();
        Map map = new HashMap();
        map.put("phone",phone);
        map.put("smsCode",smsCode);
        System.out.println(JSONObject.toJSONString(map));
        HttpEntity<MultiValueMap<String,Object>> httpEntity=new HttpEntity(JSONObject.toJSONString(map));
        String url="http://localhost:"+localPort+"/sms/login";
        System.out.println(url);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://localhost:" + localPort + "/sms/login", httpEntity, String.class);
        ApiResult parse = JSONObject.parseObject(stringResponseEntity.getBody(),ApiResult.class);
        return parse;
    }

    /**
     * 查询用户信息通过用户id
     * @param userId 用户id
     * @return
     */
    @GetMapping(value = "users")
    public User queryUser(@Min(1) Integer userId){
        User user =  userService.queryUser(userId);
        return user;
    }

    /**
     * 更新用户信息(电话/密码)
     * @param user  用户信息
     * @param userId 用户id(token解析)
     */
    @PutMapping(value = "users")
    public void updateUser(@JsonObject User user,@Min(1) Integer userId){
        user.setUserId(userId);
        userService.updateUser(user);
    }

    /**
     * 关注别人
     * @param user 关注人信息
     * @param userId 用户id(token解析)
     */
    @PostMapping(value = "users")
    public void addSupport(@JsonObject @Validated(GroupB.class) User user, @Min(1) Integer userId){
        userService.addSupport(user,userId);
    }

    /**
     *取消关注
     * @param user 关注人信息
     * @param userId 用户id(token解析)
     */
    @DeleteMapping(value = "users")
    public void deleteSupport( @JsonObject @Validated(GroupB.class) User user,@Min(1) Integer userId){
        userService.deleteSupport(user,userId);
    }


    /**
     * 查询用户(别人)信息
     * @param userId 被查询用户id
     */
    @GetMapping(value = "users/{userId}")
    public User queryUsermeta(@PathVariable("userId") @Min(1) Integer userId) {
        return userService.queryUser(userId);
    }

}
