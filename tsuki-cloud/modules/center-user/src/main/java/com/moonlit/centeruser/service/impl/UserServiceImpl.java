package com.moonlit.centeruser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.centeruser.entity.User;
import com.moonlit.centeruser.mapper.UserMapper;
import com.moonlit.centeruser.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 29/10/2022 0:50
 * @email by.Moonlit@hotmail.com
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
