package com.saybiu.service;

/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/4/16 9:56 下午 （日期和时间）
 */

public interface SmsSimulationService {
    /**
     * 模拟获取一个六位短信验证码
     * @return
     */
    String getSmsCode();
}
