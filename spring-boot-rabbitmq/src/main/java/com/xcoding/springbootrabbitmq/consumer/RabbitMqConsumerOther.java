package com.xcoding.springbootrabbitmq.consumer;

import com.xcoding.springbootrabbitmq.config.RabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 一个生产者，多个消费者，可以写多个消费者，并且他们的分发是负载均衡的
 */
@Component
@RabbitListener(queues = RabbitMqConfig.QUEUE_A)
public class RabbitMqConsumerOther {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String content){
        logger.info("Two接收处理队列A当中的消息： " + content);
    }
}
