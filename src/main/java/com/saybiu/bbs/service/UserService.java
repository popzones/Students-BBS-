package com.saybiu.bbs.service;

import com.saybiu.bbs.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 *
 */
public interface UserService extends IService<User> {
    void sendSmsAndToRedis(User user,String actionType) throws InterruptedException;
    /**
     *  微信用户注册与登录
     * @param user
     * @return
     */
    Map wxUserRegisterAndLogin(User user,String code);

    User queryUser(Integer userId);

    void updateUser(User user);

    void addSupport(User user, Integer userId);

    void deleteSupport(User user, Integer userId);

    Map userLogin(User user);

    User getUserInfo(String phone);

    User commonRegister(User user,String ip);
}
