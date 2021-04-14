package com.saybiu.controller;

import com.saybiu.service.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping
    @ResponseBody
    public void printHash(String name)
    {
        userService.setName(name);
        userService.printHash();
    }
}
