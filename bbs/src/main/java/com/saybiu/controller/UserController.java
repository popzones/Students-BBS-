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
