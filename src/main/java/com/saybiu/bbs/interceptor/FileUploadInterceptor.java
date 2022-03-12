package com.saybiu.bbs.interceptor;


import com.saybiu.bbs.exception.ServiceException;
import com.saybiu.bbs.response.CommonCode;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/4/18 8:03 下午 （日期和时间）
 */

public class FileUploadInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {

        long maxSize=5242880;
        if(request!=null && ServletFileUpload.isMultipartContent(request)) {
            ServletRequestContext ctx = new ServletRequestContext(request);
            //获取上传文件尺寸大小
            long requestSize = ctx.contentLength();
            if(requestSize==0)
            {
                throw new ServiceException(CommonCode.EMPTY_FILE);
            }
            if (requestSize > maxSize) {
                //当上传文件大小超过指定大小限制后，抛出MaxUploadSizeExceededException异常
                throw new MaxUploadSizeExceededException(maxSize);
            }
        }
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
