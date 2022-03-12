package com.saybiu.bbs.utils;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saybiu.bbs.entity.User;
import org.springframework.security.core.Authentication;
import redis.clients.jedis.Jedis;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Java web token 工具类
 *
 *
 * @author wangfei*/
public class JwtUtil {
    /**
     * 设置token过期时间(7天)
     */
    private static final long EXPIRE_TIME = 60 * 1000 * 60 * 24 * 7;
    /**
     * 设置token私钥
     */
    private final static String TOKEN_SECRET="d3d3LnNheWJpdS5jb20";

    /**
     * 生成token
     * @param user 数据库中查到的user对象
     * @return token
     */
    public static String creatToken(User user) {
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            //私钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            /**
             JWT.create()
            .withHeader(header)//头部
            .withClaim("phoneNumber", phoneNumber)//载荷
            .withClaim("userId",userId)
            .withExpiresAt(date)//过期时间
            .sign(algorithm);//signature加密算法
             */
            // 附带username，userId信息，生成签名
            String token = JWT.create().withHeader(header).withClaim("phoneNumber", user.getUserPhone()).withClaim("userId", user.getUserId())
                           .withExpiresAt(date).sign(algorithm);
            //将token放入redis
            System.out.println(token+user.getUserId());
            JedisUtils.insertRedis(token,user.getUserId());
            return token;

        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 校验token是否正确
     * @param token 令牌
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            //获取加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            //解密
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    /**
     * 通过解密token获取登陆用户ID
     * @param token
     * @return
     */
    public static int getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            //获取用户ID字符串
            return jwt.getClaim("userId").asInt();
        } catch (JWTDecodeException e) {
            return 0;
        }
    }
    /**
     * 通过解密token获取登陆用户电话
     * @param token
     * @return
     */
    public static String getUserPhoneNum(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            //获取用户电话字符串
            return jwt.getClaim("phoneNumber").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    public static String splitToken(String token)
    {
        return token.substring(7);
    }
    public  static void userInfoToRedis(String jsonString,Integer userId)
    {
        JedisUtils.insertRedis(jsonString,userId);
    }
    public static String getUserInfoFromRedis(String token){
        Jedis jedis = JedisUtils.getJedis();
        int userId = getUserId(token);
        String userInfo = jedis.get(String.valueOf(userId));
        JSONObject jsonObject = JSONObject.parseObject(userInfo);
        if(jsonObject==null)
        {
            return "NOT_EXIST";
        }
        return jsonObject.toJSONString();
    }





}
