package com.moonlit.centeruser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.centeruser.entity.UserDetail;
import com.moonlit.centeruser.entity.dto.UserDetailUpdateDTO;

/**
 * 用户详情业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 29/10/2022 15:05
 */
public interface UserDetailService extends IService<UserDetail> {

    /**
     * 新增用户详情
     *
     * @param userDetail 用户详情实体
     * @return 结果
     */
    Boolean insertUserDetail(UserDetail userDetail);

    /**
     * 修改用户详情
     *
     * @param dto 用户详情实体
     * @return 结果
     */
    Boolean updateUserDetail(UserDetailUpdateDTO dto);

}
