package com.moonlit.centeruser.constant;

import com.moonlit.common.exception.base.AbstractBaseExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Joshua
 * @version 1.0
 * @date 29/10/2022 21:18
 * @email by.Moonlit@hotmail.com
 */
@Getter
@AllArgsConstructor
public enum UserErrorEnum implements AbstractBaseExceptionEnum {

    // 异常
    USER_IS_DISABLED(1100, "用戶被禁用"),
    ACCOUNT_OR_PASSWORD_ERROR(1101, "账户或密码错误"),
    ;

    private Integer code;

    private String message;

}
