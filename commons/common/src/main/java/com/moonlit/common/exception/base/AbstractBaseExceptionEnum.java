package com.moonlit.common.exception.base;

/**
 * 异常枚举父接口类
 *
 * @author Joshua
 * @version 1.0
 * @date 21/3/2023 14:10
 * @email by.Moonlit@hotmail.com
 */
public interface AbstractBaseExceptionEnum {

    /**
     * 错误码
     *
     * @return 错误码
     */
    Integer getCode();

    /**
     * 错误信息
     *
     * @return 信息
     */
    String getMessage();

}
