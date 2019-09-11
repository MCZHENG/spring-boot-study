package com.xcoding.springbootmybatis;

import com.xcoding.springbootmybatis.mapper.UserMapper;
import com.xcoding.springbootmybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        System.out.println(userService.getUser());
    }

}
