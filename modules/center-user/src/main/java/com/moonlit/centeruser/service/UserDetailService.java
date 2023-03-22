package com.moonlit.centeruser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.centeruser.entity.UserDetail;

/**
 * 用户详情业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 23/3/2023 0:51
 * @email by.Moonlit@hotmail.com
 */
public interface UserDetailService extends IService<UserDetail> {

    /**
     * 新增用户详情
     *
     * @param userDetail 用户详情实体
     * @return 结果
     */
    Boolean insertUserDetail(UserDetail userDetail);

}
