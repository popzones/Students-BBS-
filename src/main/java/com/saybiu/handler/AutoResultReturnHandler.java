package com.saybiu.handler;
import com.alibaba.fastjson.JSONObject;
import com.saybiu.response.ApiResult;
import com.saybiu.response.CommonCode;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import java.io.File;


@ControllerAdvice
public class AutoResultReturnHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;

    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (body == null) {
          return ApiResult.SUCCESS();
        }
        else if (body instanceof ApiResult) {
            return body;
        }
        else if(body instanceof String)
        {
            serverHttpResponse.getHeaders().set("Content-Type", "application/json;charset=utf-8");
            ApiResult apiResult=new ApiResult(CommonCode.SUCCESS,body);
            return JSONObject.toJSONString(apiResult);

        }
        else if (body instanceof File)
        {
            return body;
        }
        else
        {
            return ApiResult.SUCCESS(body);
        }
    }
}
