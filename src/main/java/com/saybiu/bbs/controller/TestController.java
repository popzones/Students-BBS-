package com.saybiu.bbs.controller;

import com.saybiu.bbs.annotation.JsonObject;
import com.saybiu.bbs.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/5/4 10:20 下午 （日期和时间）
 */
@RestController
@Validated
public class TestController {
    @PreAuthorize("hasPermission('1','2')")
    @RequestMapping("test11")
    public void test11()
    {
        System.out.println("我草你妈");
    }
    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping("test22")
    public void test22()
    {

    }
    @RequestMapping("login")
    public void test33()
    {

    }
}
