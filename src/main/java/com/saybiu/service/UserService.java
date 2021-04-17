package com.saybiu.service;

import com.saybiu.domain.*;

import java.util.List;

/**
 * @Author: 李金祥
 * @Date: 2021/4/16 20:32 （日期和时间）
 */
public interface UserService {
    /**
     * 用户注册
     * @param user 用户注册信息
     * 事务处理
     * 影响bbs_users和bbs_usermeta表
     */
    void register(User user);

    /**
     * 用户登录
     * @param user
     * @param UserLatestIp 用户最近登录ip
     * @return 返回用户登录token
     */
    String login(User user, String UserLatestIp);

    /**
     * 注册用户属性信息根据user中的id
     * @param userMeta 用户属性信息
     * @param userId 用户id
     */
    void addUserMeta(Integer userId, UserMeta userMeta);

    /**
     * 修改用户手机号根据user中的id
     * @param phone 用户新手机号
     * @param userId 用户id
     */
    void updatePhone(Integer userId, String phone);

    /**
     * 修改用户密码根据user中的id
     * @param password 用户新密码
     * @param userId 用户id
     */
    void updatePassword(Integer userId, String password);

    /**
     * 修改用户头像根据user中的id
     * @param userPictureUrl 用户头像的url
     * @param userId 用户id
     */
    void updateUserPictureUrl(Integer userId, String userPictureUrl);

    /**
     * 修改用户昵称根据user中的id
     * @param userNickname 用户新昵称
     * @param userId 用户id
     */
    void updateUserNickname(Integer userId, String userNickname);


    /**
     * 关注他人和被关注
     * @param userId 用户id
     * @param beSupportedUserId 被关注人的id
     * 做事务处理
     * 影响bbs_userMeta和bbs_supports表
     */
    void userSupportAndUserBeSupported(Integer userId,Integer beSupportedUserId);
    /**
     * 用户收藏帖子
     * @param userId 用户id
     * @param collection 被收藏的信息
     * 做事务处理
     * 影响bbs_userMeta和bbs_collections表
     */
    void userSupportPost(Integer userId, Collection collection);

    /**
     * 点赞和被点赞
     * @param userId 用户id
     * @param like 被点赞的信息
     * 做事务处理
     * 影响bbs_userMeta和bbs_likes表
     */
    void userLikeAndUserBeLike(Integer userId, Like like);

    /**
     * 用户发送帖子
     * @param userId 用户id
     * @param post 帖子目录信息
     * @param postMeta 帖子内容信息
     * 做事务处理
     * 影响bbs_userMeta、bbs_posts和bbs_postmeta表
     */
    void userSendPost(Integer userId, Post post, PostMeta postMeta);

    //------------------对用户信息的查询操作-------------------------------

    /**
     * 查询用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    User queryUser(Integer userId);

    /**
     * 查询用户属性信息
     * @param userId 用户id
     * @return 返回该用户的属性实体
     * 操作bbs_usermeta表
     */
    UserMeta queryUserMeta(Integer userId);

    /**
     * 查询用户关注人和用户被关注人
     * @param userId 用户id
     * @param beSupportedUserId 被关注人id
     * @return
     * 操作bbs_supports表
     */
    List<Support> queryUserSupportAndBeSupported(Integer userId, Integer beSupportedUserId);

    /**
     * 查询用户收藏信息
     * @param userId 用户id
     * @return 返回该用户收藏信息实体
     * 操作bbs_collections表
     */
    List<Collection> queryUserCollection(Integer userId);

    /**
     * 用户查看自己发送的帖子
     * @param userId 用户id
     * @return 帖子集合
     * 操作bbs_posts表
     */
    List<Post> queryUserPost(Integer userId);

    /**
     * 查询用户点赞和被点赞
     * @param userId 用户id
     * @param beLikedUserId 被点赞人id
     * @return
     * 操作bbs_likes表
     */
    List<Like> queryUserLikeAndBeLiked(Integer userId, Integer beLikedUserId);

}
