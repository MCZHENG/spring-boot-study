package com.xcoding.springbootshiro;

import com.xcoding.springbootshiro.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootShiroApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getUser() {
//        System.out.println(userMapper.getUser());
        System.out.println( userMapper.findByUserName("Tom"));
    }

}
