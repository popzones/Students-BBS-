package com.saybiu.bbs.resolver;

import com.alibaba.fastjson.JSONObject;

import com.saybiu.bbs.annotation.JsonObject;
import com.saybiu.bbs.exception.ServiceException;
import com.saybiu.bbs.response.CommonCode;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;


/**
 * 王飞
 * 5月1日 02：10
 * @author wangfei
 */
public class MultipleJsonObjectResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(JsonObject.class);
    }

    @Override public Object resolveArgument(MethodParameter methodParameter,
                                            ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
                                            WebDataBinderFactory webDataBinderFactory) throws Exception
    {
            Object result=null;
            JSONObject jsonObject = getRequestJsonObeject(nativeWebRequest);
            //获取参数类型
            Class<?> type = methodParameter.getParameterType();
            //获取参数名字
            String name = methodParameter.getParameterName();
            if (null != jsonObject && jsonObject.containsKey(name))
            {

                result= jsonObject.getObject(name,type);
                if(methodParameter.hasParameterAnnotation(Valid.class)||methodParameter.hasParameterAnnotation(Validated.class))
                {
                    if(webDataBinderFactory!=null)
                    {
                        WebDataBinder webDataBinder=webDataBinderFactory.createBinder(nativeWebRequest,result,name);

                        if (webDataBinder.getTarget() != null) {
                            // 如果有校验报错，会将结果放在binder.bindingResult属性中
                            webDataBinder.validate();
                            Validator validator = webDataBinder.getValidator();
                            // 如果参数中不包含BindingResult参数，直接抛出异常
                            if (webDataBinder.getBindingResult().hasErrors() && this.isBindExceptionRequired(methodParameter))
                            {
                                throw new BindException(webDataBinder.getBindingResult());
                            }
                        }
                        Map bindingResultModel = webDataBinder.getBindingResult().getModel();
                        modelAndViewContainer.removeAttributes(bindingResultModel);
                        modelAndViewContainer.addAllAttributes(bindingResultModel);
                    }
                }
                return result;
            }
        throw new ServiceException(CommonCode.INVALID_PARAM,"传入的参数"+name+"不能为空");
        }



    private JSONObject getRequestJsonObeject(NativeWebRequest webRequest) throws IOException {
        JSONObject jsonObject = new JSONObject();
        HttpServletRequest httpServletRequest =
                (HttpServletRequest) webRequest.getNativeRequest(HttpServletRequest.class);
        //获取请求类型
        String method = httpServletRequest.getMethod();
        //get和delete方法无请求体,不处理
        if (!method.equals("GET"))
        {

            if (null != httpServletRequest.getAttribute("param"))
            {
                try
                {
                    jsonObject = JSONObject.parseObject(httpServletRequest.getAttribute("param").toString());
                } catch (Exception e)
                {
                    System.out.println("参数解析出现异常");
                }
            }
            else
            {
                StringBuilder buffer = new StringBuilder();
                //获取request输入流，将流中的数据封装为json对象，因为发请求的时候都是json字符串
                BufferedReader reader = httpServletRequest.getReader();
                String line;
                while ((line = reader.readLine()) != null)
                {
                    buffer.append(line);
                }
                 httpServletRequest.setAttribute("param", buffer.toString());
                try
                {
                    jsonObject = JSONObject.parseObject(buffer.toString());
                } catch (Exception e)
                {
                    System.out.println("解析json字符串出现错误");
                }
            }
        }
        else
         {
             Map<String, String[]> parameterMap = webRequest.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet())
            {
                String key = entry.getKey();
                String values = StringUtils.join(entry.getValue());
                jsonObject.put(key, values);
            }
         }

        return jsonObject;
    }
    protected boolean isBindExceptionRequired( MethodParameter methodParam) {
        int i = methodParam.getParameterIndex();
        Class[] paramTypes = methodParam.getMethod().getParameterTypes();
        boolean hasBindingResult = paramTypes.length > i + 1 && Errors.class.isAssignableFrom(paramTypes[i + 1]);
        return !hasBindingResult;
    }
}
