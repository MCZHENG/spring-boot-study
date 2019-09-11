package com.xcoding.springbootredis;


import com.xcoding.springbootredis.entity.User;
import com.xcoding.springbootredis.util.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;

public class RedisTest extends SpringBootRedisApplicationTests {

    @Autowired()
    private RedisUtil redisUtil;

    @Test
    public void setTest1(){
        User user = new User();
        user.setId(1l);
        user.setName("Mary");
        redisUtil.set("user",user);
    }

    @Test
    public void setTest2(){
        User user = new User();
        user.setId(1l);
        user.setName("Lucy");
        redisUtil.set("user",user,1000l);
    }

    @Test
    public void setTest3(){
        ArrayList list = new ArrayList();
        User user = new User();
        User user1 = new User();
        user.setId(1l);
        user.setName("Lucy");
        user1.setId(2l);
        user1.setName("Tom");
        list.add(user);
        list.add(user1);
        redisUtil.lSet("userList",list,1000l);
    }

    @Test
    public void getTest1(){
//        User user = new User();
//        user.setId(1l);
//        user.setName("Mary");
        Object o = redisUtil.get("user");
        System.out.println((User)o);
    }

    @Test
    public void getTest2(){
//        User user = new User();
//        user.setId(1l);
//        user.setName("Mary");
        Object o = redisUtil.lGet("userList",0,0);
        System.out.println(o);
    }
}
