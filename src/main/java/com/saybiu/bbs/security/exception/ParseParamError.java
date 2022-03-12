package com.saybiu.bbs.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author wangwei
 * @Date 2021/5/27 20:32
 * @Version 1.0
 */
public class ParseParamError extends AuthenticationException {
    public ParseParamError(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ParseParamError(String msg) {
        super(msg);
    }
}


