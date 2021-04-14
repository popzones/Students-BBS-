package com.saybiu.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private String name;

    public void setName(String name) {
        this.name = name;
    }
    public void printHash()
    {
        System.out.println(name);
        System.out.println(this.hashCode());
    }
}
