package com.moonlit.centeruser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moonlit.centeruser.entity.UserDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户详情映射层
 *
 * @author Joshua
 * @version 1.0
 * @date 29/10/2022 15:05
 */
@Mapper
public interface UserDetailMapper extends BaseMapper<UserDetail> {

}
