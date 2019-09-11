package com.xcoding.springbootmybatis.service;

import com.xcoding.springbootmybatis.entity.User;
import com.xcoding.springbootmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public List<User> getUser() {
        return userMapper.getUser();
    }
}
