package com.saybiu.dao;

import com.saybiu.domain.User;
import com.saybiu.domain.UserMeta;

/**
 * @Author: liming
 * @Date: 2021/4/17 14:35
 */
public interface UserMetaDao {
    /**
     *
     * @param userId
     * @return
     * 用户关注量+1
     */
    int updateAddUserLikesNum(Integer userId);
    int updateAddUserBeLikesNum(Integer userId);
    UserMeta selectUserMetaById(Integer userId);
}
