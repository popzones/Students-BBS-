package com.saybiu.bbs.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author wangwei
 * @Date 2021/5/27 20:08
 * @Version 1.0
 */
public class SmsCodeError extends AuthenticationException {
    public SmsCodeError(String msg, Throwable cause) {
        super(msg, cause);
    }

    public SmsCodeError(String msg) {
        super(msg);
    }
}
