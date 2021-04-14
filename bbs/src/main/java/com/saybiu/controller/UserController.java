package com.saybiu.controller;

import com.saybiu.exception.ServiceException;
import com.saybiu.response.CommonCode;
import com.saybiu.service.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * 全局异常处理为throw new ServiceException(CommonCode.枚举值);
 * 除ServiceException外的RuntimeException全部返回{"success":false,"data":null,"responseCode":"200000","responseMessage":"系统繁忙,请刷新或联系管理员处理"}
 * 遇到此情况，请去tomcat查看输出日志，打印出来了。
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

}
