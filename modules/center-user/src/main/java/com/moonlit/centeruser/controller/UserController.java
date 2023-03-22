package com.moonlit.centeruser.controller;

import com.moonlit.centeruser.entity.User;
import com.moonlit.centeruser.entity.dto.UserUpdateDTO;
import com.moonlit.centeruser.service.UserService;
import com.moonlit.common.response.Result;
import com.moonlit.mybatis.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 22/3/2023 22:36
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页查询用户
     *
     * @param user 用户实体
     * @return 用户集合
     */
    @GetMapping("/pageList")
    public Result<PageResult<User>> page(User user) {
        return Result.success(userService.pageList(user));
    }

    /**
     * 新增用户
     *
     * @param user 用户实体
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> addSave(@RequestBody User user) {
        return Result.success(userService.insertUser(user));
    }

    /**
     * 修改用户
     *
     * @param userUpdateDTO 用户实体
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody UserUpdateDTO userUpdateDTO) {
        return Result.success(userService.updateUser(userUpdateDTO));
    }

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(String ids) {
        return Result.success(userService.deleteUserByIds(ids));
    }
}
