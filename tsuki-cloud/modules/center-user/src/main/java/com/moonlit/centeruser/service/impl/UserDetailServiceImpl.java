package com.moonlit.centeruser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.centeruser.entity.UserDetail;
import com.moonlit.centeruser.entity.dto.UserDetailUpdateDTO;
import com.moonlit.centeruser.mapper.UserDetailMapper;
import com.moonlit.centeruser.service.UserDetailService;
import com.moonlit.centeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户详情业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 29/10/2022 15:05
 */
@Service
public class UserDetailServiceImpl extends ServiceImpl<UserDetailMapper, UserDetail> implements UserDetailService {

    @Autowired
    private UserService userService;

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

    /**
     * 修改用户详情
     *
     * @param dto 用户详情实体
     * @return 结果
     */
    @Override
    public Boolean updateUserDetail(UserDetailUpdateDTO dto) {
        UserDetail userDetail = userService.getUserVoByAccountId(dto.getAccountId()).getUserDetail();
        userDetail.setNickName(dto.getNickName());
        userDetail.setIcon(dto.getIcon());
        userDetail.setSex(dto.getSex());
        return this.updateById(userDetail);
    }


}
