package com.moonlit.common.exception;

import com.moonlit.common.exception.base.AbstractBaseExceptionEnum;
import com.moonlit.common.response.Result;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author Joshua
 * @version 1.0
 * @date 21/3/2023 13:26
 * @email by.Moonlit@hotmail.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4458413389987217863L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 异常说明
     */
    private String message;

    /**
     * 异常类
     */
    private Throwable e;

    /**
     * 通用异常
     *
     * @param code    错误码
     * @param message 异常说明
     */
    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 自定义异常
     *
     * @param exception 自定义异常类
     */
    public BusinessException(AbstractBaseExceptionEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.message = exception.getMessage();
    }

    /**
     * 系统异常
     *
     * @param e 系统异常类
     */
    public BusinessException(Exception e) {
        super(e.getMessage());
        this.code = Result.CODE_500;
        this.message = e.getMessage();
        this.e = e;
    }

    /**
     * 自定义异常
     *
     * @param code    错误码
     * @param message 异常说明
     * @param e       系统异常类
     */
    public BusinessException(Integer code, String message, Throwable e) {
        super(message);
        this.code = code;
        this.message = message;
        this.e = e;
    }

}
