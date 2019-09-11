package com.xcoding.springbootrabbitmq;

import com.xcoding.springbootrabbitmq.consumer.RabbitMqConsumer;
import com.xcoding.springbootrabbitmq.producer.RabbitMqProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRabbitmqApplicationTests {

    @Autowired
    private RabbitMqProducer producer;
    @Autowired
    private RabbitMqConsumer consumer;

    @Test
    public void contextLoads() {
    }

    @Test
    public void sendMsg() {
        for (int i = 0; i < 50; i++) {
            producer.sendMsg("这是我发送的第 "+ i + " 条数据");
        }
    }

    @Test
    public void sendAll(){
        for (int i = 0; i < 50; i++) {
            producer.sendAll("这是我发送的第 "+ i + " 条数据");
        }
    }

}
