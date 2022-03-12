package com.saybiu.bbs.controller;

import com.saybiu.bbs.annotation.JsonObject;
import com.saybiu.bbs.entity.Support;
import com.saybiu.bbs.entity.User;
import com.saybiu.bbs.service.SupportService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * @Author wangwei
 * @Date 2021/5/12 11:36
 * @Version 1.0
 */
@RestController
@Validated
public class SupportController {
    @Resource
    private SupportService supportService;
    //查询关注人
    @GetMapping(value = "supports/{currentPage}")
    public List<Support> querySupport(@Min(1) Integer userId,@Min(1) @PathVariable("currentPage") Integer currentPage){
       // System.out.println(supportService.querySupport(userId));
        return  supportService.querySupport(userId,currentPage);
    }
    //查询粉丝
    @GetMapping(value = "supports/{userId}/{currentPage}")
    public List<Support> queryFans(@PathVariable("userId") @Min(1) Integer userId,@PathVariable("currentPage") Integer currentPage){
        //System.out.println(userId);
        return supportService.queryFans(userId,currentPage);
    }
}
