package com.moonlit.centeruser.controller;

import com.moonlit.centeruser.constant.UserErrorEnum;
import com.moonlit.centeruser.entity.User;
import com.moonlit.centeruser.entity.dto.UserDTO;
import com.moonlit.centeruser.entity.vo.UserVO;
import com.moonlit.centeruser.service.UserService;
import com.moonlit.common.exception.BusinessException;
import com.moonlit.common.response.Result;
import com.moonlit.mybatis.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 29/10/2022 0:45
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
     * 查询用户
     *
     * @param accountId 用戶賬號
     * @return 用户
     */
    @GetMapping("/get{accountId}")
    public Result<UserVO> getUser(@PathVariable String accountId) {
        UserVO user = userService.getUserVoByAccountId(accountId);
        // 用戶被禁用
        if (!user.getStatus()) {
            throw new BusinessException(UserErrorEnum.USER_IS_DISABLED);
        }
        return Result.success(user);
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
     * @param userDTO 用户实体
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody UserDTO userDTO) {
        return Result.success(userService.updateUser(userDTO));
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
