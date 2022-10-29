package com.moonlit.centeruser.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户详情实体
 *
 * @author Joshua
 * @version 1.0
 * @date 29/10/2022 15:05
 * @email by.Moonlit@hotmail.com
 */
@Data
public class UserDetail implements Serializable {

    private static final long serialVersionUID = -5380794553316719154L;

    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

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

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime updateDate;

}
