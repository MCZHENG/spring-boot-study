package com.xcoding.springbootmybatis.mapper;

import com.xcoding.springbootmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    List<User> getUser();
}
