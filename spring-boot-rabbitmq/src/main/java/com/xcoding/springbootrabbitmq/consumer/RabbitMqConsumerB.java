package com.xcoding.springbootrabbitmq.consumer;

import com.xcoding.springbootrabbitmq.config.RabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitMqConfig.QUEUE_B)
public class RabbitMqConsumerB {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String content){
        logger.info("接收处理队列 B 当中的消息： " + content);
    }

}
