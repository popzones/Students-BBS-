package com.saybiu.bbs.handler;




import com.saybiu.bbs.exception.ServiceException;
import com.saybiu.bbs.response.ApiResult;
import com.saybiu.bbs.response.CommonCode;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author wangfei
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 请求接口错误时抛出此异常
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ApiResult NoHandlerFoundException(HttpServletResponse response) {
        response.setStatus(404);
        return new ApiResult(CommonCode.NOT_FOUND);
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ApiResult AccessDeniedException(HttpServletResponse response)
    {
        response.setStatus(403);
        return new ApiResult(CommonCode.NO_AUTH);
    }
    /**
     *
     * @param e
     * @return
     * 统一处理ServiceException
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ApiResult serviceException(ServiceException e)
    {
        return new ApiResult(e.getCommonCode(),e.getErrorMessage());
    }
    /**
     *
     * @param e
     * @return
     * 处理全局RuntimeException
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ApiResult runtimeException(RuntimeException e)
    {
        e.printStackTrace();
        return new ApiResult(CommonCode.SERVER_ERROR);
    }


    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public ApiResult MaxUploadSizeExceededException(Exception e)
    {
        return new ApiResult(CommonCode.MAX_UPLOAD_ERROR);
    }

    @ExceptionHandler({ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            ServletRequestBindingException.class,
            BindException.class})
    @ResponseBody
    public ApiResult<?> handleValidationException(Exception e) {
        String msg = "";
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException t = (MethodArgumentNotValidException) e;
            msg =  getBindingResultMsg(t.getBindingResult());
        } else if (e instanceof BindException) {
            BindException t = (BindException) e;
            msg = getBindingResultMsg(t.getBindingResult());
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException t = (ConstraintViolationException) e;
            msg = t.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(","));
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException t = (MissingServletRequestParameterException) e;
            msg = t.getParameterName() + " 不能为空";
        } else if (e instanceof MissingPathVariableException) {
            MissingPathVariableException t = (MissingPathVariableException) e;
            msg = t.getVariableName() + " 不能为空";
        } else {
            msg = "必填参数缺失";
        }

        return new ApiResult<>(CommonCode.INVALID_PARAM,msg);
    }
    @ExceptionHandler(ConstraintDeclarationException.class)
    @ResponseBody
    public ApiResult ConstraintDeclarationException(Exception e)
    {
        return new ApiResult(CommonCode.SERVER_ERROR,"需要验证的参数应该放在接口处声明，而不是实现类！");
    }

    private String getBindingResultMsg(BindingResult result) {
        return result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
    }


}
