package com.saybiu.controller;

import com.saybiu.exception.ServiceException;
import com.saybiu.response.CommonCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping(value = "test1")
    @ResponseBody
    public Map test()
    {
        Map map=new HashMap();
        map.put("data","123");
        return map;
    }
    @RequestMapping(value = "test2")
    @ResponseBody

    public String test2()
    {
        return "123";
    }
    @RequestMapping(value = "test3")
    @ResponseBody
    public void test3()
    {

    }
    @RequestMapping(value = "test4")
    @ResponseBody
    public void test4(HttpServletResponse response)
    {
    throw new ServiceException(CommonCode.NO_LOGIN);
    }
}
