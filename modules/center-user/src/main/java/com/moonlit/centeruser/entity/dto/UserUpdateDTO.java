package com.moonlit.centeruser.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新DTO
 *
 * @author Joshua
 * @version 1.0
 * @date 22/3/2023 23:01
 * @email by.Moonlit@hotmail.com
 */
@Data
public class UserUpdateDTO implements Serializable {

    private static final long serialVersionUID = 4860764858918532613L;

    /**
     * 账户id
     */
    private String accountId;

    /**
     * 账户
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态（0 停用，1 正常）
     */
    private Boolean status;

}