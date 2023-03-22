package com.moonlit.centeruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moonlit.centeruser.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户映射层
 *
 * @author Joshua
 * @version 1.0
 * @date 22/3/2023 22:39
 * @email by.Moonlit@hotmail.com
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
