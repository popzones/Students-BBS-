package com.saybiu.service;

import com.saybiu.exception.ServiceException;
import com.saybiu.response.CommonCode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private String name;

    public void setName(String name) {
        this.name = name;
        throw new ServiceException(CommonCode.NO_AUTH);
    }
    public void printHash()
    {
        System.out.println(name);
        System.out.println(this.hashCode());
    }
}
