package com.xcoding.springbootshiro.dao;

import com.xcoding.springbootshiro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    User findByUserName(@Param("userName") String userName);
    List<User> getUser();
}
