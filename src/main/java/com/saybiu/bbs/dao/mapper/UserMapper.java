package com.saybiu.bbs.dao.mapper;

import com.saybiu.bbs.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.saybiu.bbs.entity.User
 */
public interface UserMapper extends BaseMapper<User> {
    //查询用户信息以及用户属性
    User queryUser(Integer userId);
    User selectUserByPhone(String phone);
}




