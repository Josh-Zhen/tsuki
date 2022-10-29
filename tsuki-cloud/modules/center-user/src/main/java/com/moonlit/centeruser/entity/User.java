package com.moonlit.centeruser.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.moonlit.centeruser.entity.dto.UserUpdateDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实体
 *
 * @author Joshua
 * @version 1.0
 * @date 29/10/2022 0:45
 * @email by.Moonlit@hotmail.com
 */
@Data
@NoArgsConstructor
@TableName("center_user")
public class User implements Serializable {

    private static final long serialVersionUID = 8569273899383066811L;

    /**
     * id
     */
    private Long id;

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

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime updateDate;

    /**
     * 更新用户实体
     *
     * @param user 用戶信息
     */
    public User(UserUpdateDTO user) {
        this.accountId = user.getAccountId();
        this.account = user.getAccount();
        this.password = user.getPassword();
        this.status = user.getStatus();
    }

}
