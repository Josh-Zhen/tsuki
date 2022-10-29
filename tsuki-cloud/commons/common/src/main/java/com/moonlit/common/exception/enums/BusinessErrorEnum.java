package com.moonlit.common.exception.enums;

import com.moonlit.common.exception.base.AbstractBaseExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务枚举类
 *
 * @author Joshua
 * @version 1.0
 * @date 29/10/2022 16:18
 * @email by.Moonlit@hotmail.com
 */
@Getter
@AllArgsConstructor
public enum BusinessErrorEnum implements AbstractBaseExceptionEnum {

    // 异常
    INSERT_ERROR(1000, "新增失败"),
    UPDATE_EMPTY(1001, "更新失败"),
    DELETE_EMPTY(1002, "删除失败"),
    ;

    private Integer code;

    private String message;

}
