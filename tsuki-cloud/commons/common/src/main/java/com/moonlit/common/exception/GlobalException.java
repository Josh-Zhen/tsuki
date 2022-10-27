package com.moonlit.common.exception;

import com.moonlit.common.exception.enums.CommonErrorEnum;
import com.moonlit.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常类
 *
 * @author Joshua
 * @version 1.0
 * @date 27/10/2022 22:45
 * @email by.Moonlit@hotmail.com
 */
@Slf4j
@ControllerAdvice
public class GlobalException {

    /**
     * 全局异常处理
     *
     * @return 异常处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<CommonErrorEnum> exceptionHandler(Exception e) {
        log.error("exceptionHandler: ", e);
        String msg = specialExceptionResolve(e) == null ? "哎呀，出问题啦" : specialExceptionResolve(e);
        return Result.fail(CommonErrorEnum.DEFAULT_ERROR.getCode(), msg);
    }

    /**
     * 系统自定义异常处理
     *
     * @param e 异常类
     * @return 异常处理
     */
    @ResponseBody
    @ExceptionHandler({BusinessException.class})
    public Result<Object> handleMicrosException(BusinessException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }

    /**
     * 加入自定义处理，实现对400， 404， 405， 406， 415， 500(参数问题导致)， 503的处理
     *
     * @param e 异常信息
     * @return 结果
     */
    private String specialExceptionResolve(Exception e) {
        if (e instanceof NoHandlerFoundException) {
            return e.getMessage();
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return e.getMessage();
        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            return e.getMessage();
        } else if (e instanceof HttpMediaTypeNotAcceptableException) {
            return e.getMessage();
        } else if (e instanceof MissingPathVariableException) {
            return e.getMessage();
        } else if (e instanceof MissingServletRequestParameterException) {
            return e.getMessage();
        } else if (e instanceof ServletRequestBindingException) {
            return e.getMessage();
        } else if (e instanceof ConversionNotSupportedException) {
            return e.getMessage();
        } else if (e instanceof TypeMismatchException) {
            return e.getMessage();
        } else if (e instanceof HttpMessageNotReadableException) {
            return e.getMessage();
        } else if (e instanceof HttpMessageNotWritableException) {
            return e.getMessage();
        } else if (e instanceof MissingServletRequestPartException) {
            return e.getMessage();
        } else if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                return fieldError.getDefaultMessage();
            }
        } else if (e instanceof BindException) {
            BindingResult bindingResult = ((BindException) e).getBindingResult();
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                return fieldError.getDefaultMessage();
            }
        } else if (e instanceof AsyncRequestTimeoutException) {
            return e.getMessage();
        }
        return null;
    }

}
