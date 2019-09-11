package com.xcoding.springbootmongodb;

import com.xcoding.springbootmongodb.entity.User;
import com.xcoding.springbootmongodb.properties.MongoDBProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMongodbApplicationTests {

    @Autowired
    MongoDBProperties mongoDBProperties;
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
        User u = new User(2l,"Mary");
        mongoTemplate.insert(u);
        System.out.println(mongoTemplate.findById(1l,User.class));
    }

    @Test
    public void update() {
        Query query = Query.query(Criteria.where("id").is(1l));
        Update update = new Update();
        update.set("name","TomBoy");
        mongoTemplate.updateMulti(query,update,User.class);
    }

    @Test
    public void remove() {
       Query query = Query.query(Criteria.where("id").is(2l));
       System.out.println(mongoTemplate.remove(query,User.class));
    }

}
