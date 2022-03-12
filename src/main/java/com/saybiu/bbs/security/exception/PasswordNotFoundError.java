package com.saybiu.bbs.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author wangwei
 * @Date 2021/5/28 10:28
 * @Version 1.0
 */
public class PasswordNotFoundError extends AuthenticationException {
    public PasswordNotFoundError(String msg, Throwable cause) {
        super(msg, cause);
    }

    public PasswordNotFoundError(String msg) {
        super(msg);
    }
}
