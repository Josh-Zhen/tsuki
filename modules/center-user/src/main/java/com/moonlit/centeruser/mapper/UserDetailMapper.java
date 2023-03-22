package com.moonlit.centeruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moonlit.centeruser.entity.UserDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户详情映射层
 *
 * @author Joshua
 * @version 1.0
 * @date 23/3/2023 0:50
 * @email by.Moonlit@hotmail.com
 */
@Mapper
public interface UserDetailMapper extends BaseMapper<UserDetail> {

}
