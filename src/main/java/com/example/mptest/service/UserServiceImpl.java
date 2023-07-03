package com.example.mptest.service;

import com.example.mptest.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void handle() {

    }
}
