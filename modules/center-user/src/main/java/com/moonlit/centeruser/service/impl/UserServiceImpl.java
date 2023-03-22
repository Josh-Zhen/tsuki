package com.moonlit.centeruser.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.centeruser.entity.User;
import com.moonlit.centeruser.entity.UserDetail;
import com.moonlit.centeruser.entity.dto.UserUpdateDTO;
import com.moonlit.centeruser.mapper.UserMapper;
import com.moonlit.centeruser.service.UserDetailService;
import com.moonlit.centeruser.service.UserService;
import com.moonlit.common.exception.BusinessException;
import com.moonlit.common.exception.enums.GeneralBusinessErrorEnum;
import com.moonlit.mybatis.page.PageFactory;
import com.moonlit.mybatis.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

/**
 * 用户业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 22/3/2023 22:41
 * @email by.Moonlit@hotmail.com
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserDetailService userDetailService;

    /**
     * 分页查询用户
     *
     * @param user 用户实体
     * @return 用户集合
     */
    @Override
    public PageResult<User> pageList(User user) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotNull(user)) {
            if (ObjectUtil.isNotEmpty(user.getAccountId())) {
                queryWrapper.eq(User::getAccountId, user.getAccountId());
            }
            if (ObjectUtil.isNotEmpty(user.getAccount())) {
                queryWrapper.eq(User::getAccount, user.getAccount());
            }
            if (ObjectUtil.isNotEmpty(user.getStatus())) {
                queryWrapper.eq(User::getStatus, user.getStatus());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 查询用户
     *
     * @param accountId 用户id
     * @return 用户
     */
    @Override
    public User getUserByAccountId(String accountId) {
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();
        query.eq(User::getAccountId, accountId);
        return this.getOne(query);
    }

    /**
     * 新增用户
     *
     * @param user 用户实体
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertUser(User user) {
        try {
            user.setAccountId(IdUtil.getSnowflake().nextIdStr());
            user.setCreateDate(LocalDateTime.now());
            this.save(user);
            UserDetail detail = new UserDetail();
            detail.setUserId(user.getId());
            return userDetailService.insertUserDetail(detail);
        } catch (Exception e) {
            throw new BusinessException(GeneralBusinessErrorEnum.INSERT_ERROR.getCode(), e.getMessage());
        }
    }

    /**
     * 修改用户
     *
     * @param userUpdateDTO 用户实体
     * @return 结果
     */
    @Override
    public Boolean updateUser(UserUpdateDTO userUpdateDTO) {
        User user = this.getUserByAccountId(userUpdateDTO.getAccountId());
        user.setAccount(userUpdateDTO.getAccount());
        user.setStatus(userUpdateDTO.getStatus());
        return this.updateById(user);
    }

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteUserByIds(String ids) {
        Collection<String> list = Arrays.asList(Convert.toStrArray(ids));
        LambdaQueryWrapper<UserDetail> query = Wrappers.lambdaQuery();
        query.in(UserDetail::getUserId, list);
        userDetailService.remove(query);
        return this.removeByIds(list);
    }
}
