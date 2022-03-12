package com.saybiu.bbs.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author wangwei
 * @Date 2021/5/27 20:18
 * @Version 1.0
 */
public class SmsCodeNotFound extends AuthenticationException {
    public SmsCodeNotFound(String msg, Throwable cause) {
        super(msg, cause);
    }

    public SmsCodeNotFound(String msg) {
        super(msg);
    }
}
