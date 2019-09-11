package com.xcoding.springbootshiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xcoding.**.dao")
@SpringBootApplication
public class SpringBootShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShiroApplication.class, args);
    }

}
