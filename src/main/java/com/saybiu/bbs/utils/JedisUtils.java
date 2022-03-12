package com.saybiu.bbs.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author wangfei
 */
public class JedisUtils {
    private static JedisPool jedisPool;

    static {
        InputStream inputStream = JedisUtils.class.getClassLoader().getResourceAsStream("config/jedis.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取数据，设置到JedisPoolConfig中
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));

        //初始化JedisPool
        jedisPool = new JedisPool(config, properties.getProperty("host"), Integer.parseInt(properties.getProperty("port")));
    }

    /**
     * 获取连接方法
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 关闭Jedis
     */
    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
    public static void insertRedis(String jsonString,Integer userId)
    {
        //存入redis
        Jedis jedis = JedisUtils.getJedis();
        jedis.set(String.valueOf(userId),jsonString);
        //设置redis中的过期时间(30)
        /**
         *  nx是不存在时才set， xx是存在时才set， ex是秒，px是毫秒
         */
        jedis.set(String.valueOf(userId),jsonString,"xx","ex",1800);
    }
    public static void insertSms(String phoneNumber,String jsonStr)
    {
        //存入redis
        Jedis jedis = JedisUtils.getJedis();
        jedis.set(phoneNumber,jsonStr);
        //jedis.set(phoneNumber,jsonStr,"nx","ex",1800);
        //设置redis中的过期时间(30)
        /**
         *  nx是不存在时才set， xx是存在时才set， ex是秒，px是毫秒
         */

    }
}
