package com.saybiu.service.serviceImpl;

import com.saybiu.dao.SupportDao;
import com.saybiu.dao.UserDao;
import com.saybiu.dao.UserMetaDao;
import com.saybiu.domain.*;
import com.saybiu.exception.ServiceException;
import com.saybiu.response.CommonCode;
import com.saybiu.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/4/17 3:40 下午 （日期和时间）
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMetaDao userMetaDao;
    @Resource
    private SupportDao supportDao;
    @Resource
    private UserDao userDao;

    @Override
    public void register(User user) {

    }

    @Override
    public String login(User user, String UserLatestIp) {
        return null;
    }

    @Override
    public void addUserMeta(Integer userId, UserMeta userMeta) {

    }

    @Override
    public void updatePhone(Integer userId, String phone) {

    }

    @Override
    public void updatePassword(Integer userId, String password) {

    }

    @Override
    public void updateUserPictureUrl(Integer userId, String userPictureUrl) {

    }

    @Override
    public void updateUserNickname(Integer userId, String userNickname) {

    }

    /**
     *
     * @param userId 用户id
     * @param beSupportedUserId 被关注人的id
     * 做事务处理
     * 已做事务处理
     */
    @Override
    public void userSupportAndUserBeSupported(Integer userId,Integer beSupportedUserId) {
        //获取关注人昵称
        UserMeta userMeta= userMetaDao.selectUserMetaById(userId);
        UserMeta beSupportdUserMeta=userMetaDao.selectUserMetaById(beSupportedUserId);
        if(userMeta==null||beSupportdUserMeta==null)
        {
            throw new ServiceException(CommonCode.SELECT_ERROR);
        }
        try
        {
            //关注者关注量+1
            userMetaDao.updateAddUserLikesNum(userId);
            //被关注者被关注量+1
            userMetaDao.updateAddUserBeLikesNum(beSupportedUserId);
            //关注表数据+1
            Support support=new Support();
            support.setUserSupportId(userId);
            support.setUserSupportedId(beSupportedUserId);
            support.setUserSupportNickname(userMeta.getUserNickName());
            support.setUserSupportedNickname(beSupportdUserMeta.getUserNickName());
            System.out.println(userMeta);
            System.out.println(support);
            //关注表里不能出现重复关注的数据
            if(supportDao.selectSupportByEachUserId(userMeta.getUserId(),beSupportdUserMeta.getUserId())==0)
            {
                supportDao.insertSupport(support);
                return;
            }
            //抛出重复关注的异常
            else
            {
                throw new ServiceException(CommonCode.INSERT_REPEAT);
            }


        }
        catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(CommonCode.INSERT_ERROR);
        }
    }

    @Override
    public void userSupportPost(Integer userId, Collection collection) {

    }

    @Override
    public void userLikeAndUserBeLike(Integer userId, Like like) {

    }

    @Override
    public void userSendPost(Integer userId, Post post, PostMeta postMeta) {

    }

    @Override
    public User queryUser(Integer userId) {
        return null;
    }

    @Override
    public UserMeta queryUserMeta(Integer userId) {
        return null;
    }

    @Override
    public List<Support> queryUserSupportAndBeSupported(Integer userId, Integer beSupportedUserId) {
        return null;
    }

    @Override
    public List<Collection> queryUserCollection(Integer userId) {
        return null;
    }

    @Override
    public List<Post> queryUserPost(Integer userId) {
        return null;
    }

    @Override
    public List<Like> queryUserLikeAndBeLiked(Integer userId, Integer beLikedUserId) {
        return null;
    }
}
