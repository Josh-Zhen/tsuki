package com.moonlit.centeruser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.centeruser.entity.User;
import com.moonlit.centeruser.entity.dto.UserUpdateDTO;
import com.moonlit.mybatis.page.PageResult;

/**
 * 用户业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 22/3/2023 22:40
 * @email by.Moonlit@hotmail.com
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询用户
     *
     * @param user 用户实体
     * @return 用户集合
     */
    PageResult<User> pageList(User user);

    /**
     * 查询用户
     *
     * @param accountId 用户id
     * @return 用户
     */
    User getUserByAccountId(String accountId);

    /**
     * 新增用户
     *
     * @param user 用户实体
     * @return 结果
     */
    Boolean insertUser(User user);

    /**
     * 修改用户
     *
     * @param userUpdateDTO 用户实体
     * @return 结果
     */
    Boolean updateUser(UserUpdateDTO userUpdateDTO);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteUserByIds(String ids);


}
