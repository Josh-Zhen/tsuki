package com.moonlit.centeruser.controller;

import com.moonlit.centeruser.entity.dto.UserDetailUpdateDTO;
import com.moonlit.centeruser.service.UserDetailService;
import com.moonlit.common.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户详情控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 29/10/2022 15:05
 */
@RestController
@RequestMapping("/userDetail")
public class UserDetailController {

    @Autowired
    private UserDetailService userDetailService;

    /**
     * 修改用户详情
     *
     * @param dto 用户详情实体
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody UserDetailUpdateDTO dto) {
        return Result.success(userDetailService.updateUserDetail(dto));
    }

}
