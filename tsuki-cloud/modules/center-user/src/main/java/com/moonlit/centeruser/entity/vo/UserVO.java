package com.moonlit.centeruser.entity.vo;

import com.moonlit.centeruser.entity.UserDetail;
import lombok.Data;

import java.io.Serializable;

/**
 * 用戶VO实体
 *
 * @author Joshua
 * @version 1.0
 * @date 29/10/2022 15:52
 * @email by.Moonlit@hotmail.com
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = -7915267579258816869L;

    /**
     * 账户id
     */
    private String accountId;

    /**
     * 账户
     */
    private String account;

    /**
     * 状态（0 停用，1 正常）
     */
    private Boolean status;

    /**
     * 用户详情l
     */
    private UserDetail userDetail;

}
