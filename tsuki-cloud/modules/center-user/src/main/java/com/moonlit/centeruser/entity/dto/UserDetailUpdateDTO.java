package com.moonlit.centeruser.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户详情更新DTO
 *
 * @author Joshua
 * @version 1.0
 * @date 29/10/2022 15:35
 * @email by.Moonlit@hotmail.com
 */
@Data
public class UserDetailUpdateDTO implements Serializable {

    private static final long serialVersionUID = -5195606434605656285L;

    /**
     * 账户id
     */
    private String accountId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String icon;

    /**
     * 性別
     */
    private Integer sex;

}
