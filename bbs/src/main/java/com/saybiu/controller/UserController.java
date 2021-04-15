package com.saybiu.controller;

import com.saybiu.exception.ServiceException;
import com.saybiu.response.CommonCode;
import com.saybiu.service.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
@RequestMapping(value="test1")
@ResponseBody

/**
 * 如果返回类型为string，则会返回{"data":data,"responseCode":"000000","responseMessage":"请求成功","success":true}
 * 如果返回类型为对象；如map，object，等，则会返回{"success":true,"data":{"data":123},"responseCode":"000000","responseMessage":"请求成功"}
 * 如果返回类型为void，则只会返回{"success":true,"data":null,"responseCode":"000000","responseMessage":"请求成功"}
 * 除ServiceException外的RuntimeException全部返回{"success":false,"data":null,"responseCode":"200000","responseMessage":"系统繁忙,请刷新或联系管理员处理"}
 * 遇到此情况，请去tomcat查看输出日志，打印出来了。
 *
 *
 * 自定义异常处理请见test4
 *
 * 请使用@RequestParam(required=true)，若参数未传入，则会抛出异常，此异常我已捕获统一处理。
 * {"success":false,"data":"有必传参数未传入","responseCode":"100001","responseMessage":"参数不完整或不正确"}
 *
 * 若传入参数类型不一致，则会抛出异常，此异常我已捕获统一处理
 * {"success":false,"data":"传入参数类型错误","responseCode":"100001","responseMessage":"参数不完整或不正确"}
 *
 * 若要自定义异常，请在GlobalExceptionHandler声明方法，并写好注释！
 */
    public Map test1()
{
    Map map=new HashMap<>();
    map.put("data",123);
    return map;
}
    @RequestMapping(value="test2")
    @ResponseBody
    public String test2()
    {

        return "123";
    }
    @RequestMapping(value="test3")
    @ResponseBody
    public void test3()
    {

    }
    @RequestMapping(value="test4")
    @ResponseBody
    public void test4()
    {
        throw new ServiceException(CommonCode.SERVER_ERROR);
    }
    @RequestMapping(value="test5")
    @ResponseBody
    public void test5(@RequestParam(required = true) Integer name)
    {
        System.out.println(name);
    }


}
