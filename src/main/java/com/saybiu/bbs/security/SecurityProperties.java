package com.saybiu.bbs.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/5/30 2:31 下午 （日期和时间）
 */
@Data
@ConfigurationProperties("custom.security")
public class SecurityProperties {
    private String[] openApi;
}
