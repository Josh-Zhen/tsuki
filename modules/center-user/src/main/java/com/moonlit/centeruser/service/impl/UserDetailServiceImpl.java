package com.moonlit.centeruser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.centeruser.entity.UserDetail;
import com.moonlit.centeruser.mapper.UserDetailMapper;
import com.moonlit.centeruser.service.UserDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户详情业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 23/3/2023 0:53
 * @email by.Moonlit@hotmail.com
 */
@Service
public class UserDetailServiceImpl extends ServiceImpl<UserDetailMapper, UserDetail> implements UserDetailService {

    /**
     * 新增用户详情
     *
     * @param userDetail 用户详情实体
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertUserDetail(UserDetail userDetail) {
        return this.save(userDetail);
    }

}
