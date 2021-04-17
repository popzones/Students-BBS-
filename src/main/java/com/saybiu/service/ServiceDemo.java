package com.saybiu.service;

import com.saybiu.exception.ServiceException;
import com.saybiu.response.CommonCode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class ServiceDemo {
    private String name;

    public void setName(String name) {
        this.name = name;
        /**
         * 自定义异常也可以在Service内抛出,但勿在service中抛出，请严格按照mvc设计模式。
         */
        throw new ServiceException(CommonCode.NO_AUTH);
    }
    public void printHash()
    {
        System.out.println(name);
        System.out.println(this.hashCode());
    }
}
