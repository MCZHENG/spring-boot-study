package com.xcoding.springbootmongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
//@Document(collection = "user") //设置collection名称，默认表名
public class User {
    @Id
    private long id;
    private String name;

    public User(long id,String name){
        this.id = id;
        this.name = name;
    }
}
