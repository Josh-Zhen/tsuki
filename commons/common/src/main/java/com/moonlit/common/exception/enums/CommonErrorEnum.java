package com.moonlit.common.exception.enums;

import com.moonlit.common.exception.base.AbstractBaseExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 公共通用异常枚举类
 *
 * @author Joshua
 * @version 1.0
 * @date 21/3/2023 14:12
 * @email by.Moonlit@hotmail.com
 */
@Getter
@AllArgsConstructor
public enum CommonErrorEnum implements AbstractBaseExceptionEnum {

    // 异常
    DEFAULT_ERROR(-1, "系统未知错误"),
    OBJECT_EMPTY(0, "对象为空"),
    ;

    private Integer code;

    private String message;

}
