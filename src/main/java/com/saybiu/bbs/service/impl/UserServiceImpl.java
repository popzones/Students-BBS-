package com.saybiu.bbs.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.saybiu.bbs.dao.mapper.SupportMapper;
import com.saybiu.bbs.dao.mapper.UsermetaMapper;
import com.saybiu.bbs.entity.SmsCode;
import com.saybiu.bbs.entity.Support;
import com.saybiu.bbs.entity.User;
import com.saybiu.bbs.entity.Usermeta;
import com.saybiu.bbs.exception.ServiceException;
import com.saybiu.bbs.response.CommonCode;
import com.saybiu.bbs.service.UserService;
import com.saybiu.bbs.dao.mapper.UserMapper;
import com.saybiu.bbs.utils.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
implements UserService{
    @Resource
    private  UserMapper userMapper;
    @Resource
    private UsermetaMapper usermetaMapper;
    @Resource
    private SupportMapper supportMapper;

    /**
     * 发送短信验证码并存储到redis
     * @param user
     * @throws InterruptedException
     */
    @Override
    public void sendSmsAndToRedis(User user,String actionType) throws InterruptedException {
        String phone;
        if(user!=null)
        {
            if(user.getUserPhone()!=null)
            {
                    phone = user.getUserPhone();
                    //判断短信是否符合规定
                    if(PhoneCheck.isChinaPhoneLegal(phone)==false)
                    {
                        throw new ServiceException(CommonCode.INVALID_PARAM,"手机号可能有误或者为新号段，请联系管理员处理");
                    }
                    Jedis jedis = JedisUtils.getJedis();
                    String phoneDate = jedis.get(phone);
                    //判断redis里面是否有上一条的数据
                    if (phoneDate == null) {
                        //产生的验证码
                        JSONObject resultJson = TencentSmsUtil.complete(phone);
                        if (resultJson != null) {
                            if (resultJson.getJSONArray("SendStatusSet").getJSONObject(0).getString("Code").equals("Ok")) {
                                String dynamicCode = resultJson.getString("dynamicCode");
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("date", Utils.toDate(new Date()));
                                jsonObject.put("dynamicCode", dynamicCode);
                                jsonObject.put("actionType",actionType);
                                JedisUtils.insertSms(phone, jsonObject.toJSONString());
                                return;
                            } else if (resultJson.getJSONArray("SendStatusSet").getJSONObject(0).getString("Code").contains("LimitExceeded")) {
                                throw new ServiceException(CommonCode.FAIL, "短信发送失败，今日发送次数已超限额");
                            } else {
                                throw new ServiceException(CommonCode.FAIL, "短信发送失败，请联系管理员");
                            }
                        } else {
                            throw new ServiceException(CommonCode.FAIL, "短信发送失败，请联系管理员");
                        }
                    }
                    //如果redis里有上一条数据，在这里判断是否时间超过60秒
                    else {
                        String date = JSONObject.parseObject(phoneDate).getString("date");
                        if (Utils.overTime(date, Utils.toDate(new Date()), 60))
                        {
                            JSONObject resultJson = TencentSmsUtil.complete(phone);
                            if (resultJson != null) {
                                if (resultJson.getJSONArray("SendStatusSet").getJSONObject(0).getString("Code").equals("Ok")) {
                                    String dynamicCode = resultJson.getString("dynamicCode");
                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("date", Utils.toDate(new Date()));
                                    jsonObject.put("dynamicCode", dynamicCode);
                                    jsonObject.put("actionType",actionType);
                                    JedisUtils.insertSms(phone, jsonObject.toJSONString());
                                    return;
                                } else if (resultJson.getJSONArray("SendStatusSet").getJSONObject(0).getString("Code").contains("LimitExceeded")) {
                                    throw new ServiceException(CommonCode.FAIL, "短信发送失败，今日发送次数已超限额");
                                }
                            } else {
                                throw new ServiceException(CommonCode.FAIL, "短信发送失败，请联系管理员处理");
                            }
                        }
                        else {
                            throw new ServiceException(CommonCode.FAIL, "短信发送频率过快，每分钟只能发一次哦！");
                        }
                    }
            }
            throw new ServiceException(CommonCode.INVALID_PARAM,"用户手机号不能为空");
        }
        else
        {
            throw new ServiceException(CommonCode.INVALID_PARAM,"传入的用户对象不能为空");
        }

    }
    /**
     * 注册
     * @param user
     * @param ip
     * @return
     */
    @Override
    public User commonRegister(User user, String ip) {
        user.setUserRegisterIp(ip);
        int insert1 = userMapper.insert(user);
        if (insert1==0){
            throw new ServiceException(CommonCode.INSERT_ERROR,"注册失败");
        }
        Usermeta usermeta = new Usermeta();
        usermeta.setUserId(user.getUserId());
        int insert2 = usermetaMapper.insert(usermeta);
        if (insert2==0) {
            throw new ServiceException(CommonCode.INSERT_ERROR,"注册失败");
        }
        return user;
    }

    /**
     * 微信注册
     * @param user
     * @return
     */
    @Override
    public Map wxUserRegisterAndLogin(User user,String code)
    {

        Map userMap=new HashMap();
        if (user.getUserPhone() != null)
        {
               if (userMapper.selectOne(new QueryWrapper<>(new User()).eq("user_phone", user.getUserPhone())) == null)
               {
                   user.setUserRegisterIp(user.getUserRegisterIp());
                   userMapper.insert(user);
                   Usermeta userMeta=new Usermeta();
                   userMeta.setUserId(user.getUserId());
                   usermetaMapper.insert(userMeta);
                   userMap.put("type","new");
                   //创建token
                   String token= JwtUtil.creatToken(user);
                   JedisUtils.insertRedis(token, user.getUserId());
                   userMap.put("token",token);
               }
               else
               {
                   user=userMapper.selectOne(new QueryWrapper<>(user));
                   //更新用户最近一次登录时间
                   userMapper.updateById(user);
                   Usermeta userMeta=new Usermeta();
                   userMeta.setUserId(user.getUserId());
                   usermetaMapper.updateById(userMeta);
                   userMap.put("type","old");
                   String token=JwtUtil.creatToken(user);
                   JedisUtils.insertRedis(token, user.getUserId());
                   userMap.put("token",token);
               }
               return userMap;
        }
        throw new ServiceException(CommonCode.SERVER_ERROR);
    }

    /**
     * 查询用户信息通过用户id(双表查询)
     * @param userId 用户id
     * @return
     */
    @Override
    public User queryUser(Integer userId) {
        User user = userMapper.queryUser(userId);
        if (user==null){
            throw new ServiceException(CommonCode.SELECT_ERROR,"用户不存在");
        }
        return user;
    }

    /**
     * 更新用户信息(密码/电话)
     * @param user 用户对象
     */
    @Override
    public void updateUser(User user) {
        //数据库中的信息
        User u = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", user.getUserId()));
       // User u = userMapper.queryUser(user.getUserId());
        //先判断电话号码是否为空
        if(user.getUserPhone()!=null){
            //判断是否相等
            if (u.getUserPhone().equals(user.getUserPhone())){
                throw new ServiceException(CommonCode.UPDATE_ERROR,"电话号与之前相同,请重新输入");
            }
        }else
        //判断密码是否为空
        if(user.getUserPassword()!=null){
            if (user.getUserPassword().equals(u.getUserPassword())){
                throw new ServiceException(CommonCode.UPDATE_ERROR,"密码与之前相同,请重新输入");
            }
        }else{
            throw new ServiceException(CommonCode.INVALID_PARAM,"请输入需要修改的密码或者电话");
        }
        //修改内容
        int result = userMapper.updateById(user);
        if(result==0){
            throw new ServiceException(CommonCode.UPDATE_ERROR,"修改失败");
        }
    }

    /**
     * 关注用户
     * @param user 被关注人信息
     * @param userId 用户id
     *    事务处理
     */
    @Override
    public void addSupport(User user, Integer userId) {
        if (userId.equals(user.getUserId())){
            throw new ServiceException(CommonCode.FAIL,"不能自己关注自己");
        }
        //通过userid更新用户属性表关注数量
        //int result1 = usermetaMapper.addSupportNum(userId);
        if (usermetaMapper.addSupportNum(userId)==0) {
            throw new ServiceException(CommonCode.UPDATE_ERROR,"用户关注数量加一失败");
        }
        //通过beSupportedUserId更新被关注用户粉丝数量
        //int result2 = usermetaMapper.addSupportedNum(user.getUserId());
        if(usermetaMapper.addSupportedNum(user.getUserId())==0)
        {
            throw new ServiceException(CommonCode.UPDATE_ERROR,"被关注用户粉丝数量加一失败");
        }
        //先查询关注关系是否存在
        Support support = supportMapper.selectOne(new QueryWrapper<Support>().and(wapper -> wapper.eq("user_id",userId).in("supported_user_id",user.getUserId())));
        //存在抛异常
        if (support != null) {
            throw new ServiceException(CommonCode.INSERT_REPEAT, "已经关注,不能重复关注");
        }
        //插入数据
        Support s = new Support();
        s.setUserId(userId);
        s.setSupportedUserId(user.getUserId());
        int insert = supportMapper.insert(s);
        if (insert == 0){
            throw new ServiceException(CommonCode.INSERT_ERROR,"关注失败!");
        }


    }

    /**
     * 取消关注用户
     * @param user 被关注用户信息
     * @param userId 用户id
     * 事务处理
     */
    @Override
    public void deleteSupport(User user, Integer userId) {
        if (userId.equals(user.getUserId())){
            throw new ServiceException(CommonCode.FAIL,"不能自己取消关注自己");
        }
        //更新用户属性的关注以及粉丝数量
        //int result1 = usermetaMapper.deleteSupportNum(userId);
        if (usermetaMapper.deleteSupportNum(userId)==0) {
            throw new ServiceException(CommonCode.UPDATE_ERROR,"用户关注数量减一失败");
        }
        //int result2 = usermetaMapper.deleteSupportNum(user.getUserId());
        if(usermetaMapper.deleteSupportedNum(user.getUserId())==0)
        {
            throw new ServiceException(CommonCode.UPDATE_ERROR,"被关注用户粉丝数量减一失败");
        }
        //先查询关注关系是否存在
        Support support = supportMapper.selectOne(new QueryWrapper<Support>().and(wapper -> wapper.eq("user_id",userId).in("supported_user_id",user.getUserId())));
        //存在抛异常
        if (support == null) {
            throw new ServiceException(CommonCode.INSERT_REPEAT, "不存在的关注关系");
        }
        //删除数据
        int delete = supportMapper.delete(new QueryWrapper<Support>().and(wapper -> wapper.eq("user_id", userId).in("supported_user_id", user.getUserId())));
        if (delete == 0){
            throw new ServiceException(CommonCode.INSERT_ERROR,"取消关注失败!");
        }
    }
    //用户登录
    @Override
    public Map userLogin(User user) {
        Map userMap = new HashMap();
        System.out.println(user);
        user=userMapper.selectOne(new QueryWrapper<>(user));
        //更新用户最近一次登录时间
        userMapper.updateById(user);
        Usermeta userMeta=new Usermeta();
        userMeta.setUserId(user.getUserId());
        usermetaMapper.updateById(userMeta);
        userMap.put("type","old");
        String token=JwtUtil.creatToken(user);
        JedisUtils.insertRedis(token, user.getUserId());
        userMap.put("token",token);
        return userMap;
    }

    @Override
    public User getUserInfo(String phone) {
        User user =userMapper.selectOne(new QueryWrapper<>(new User()).eq("user_phone",phone));
        if(user!=null)
        {
            return user;
        }
        throw new ServiceException(CommonCode.FAIL,"用户不存在");
    }


}




