package com.saybiu.bbs.annotation;

import java.lang.annotation.*;

/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/4/25 12:14 上午 （日期和时间）
 */
//目标：参数
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonObject {
}
