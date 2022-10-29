package com.moonlit.centeruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moonlit.centeruser.entity.User;
import com.moonlit.centeruser.entity.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户映射层
 *
 * @author Joshua
 * @version 1.0
 * @date 29/10/2022 0:45
 * @email by.Moonlit@hotmail.com
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户
     *
     * @param accountId 用户id
     * @return 用户
     */
    UserDTO getUserByAccountId(String accountId);

}
