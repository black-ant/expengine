package com.gang.etl.out.msgqueue.mq;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Classname RabbitMQLogic
 * @Description TODO
 * @Date 2020/1/29 21:34
 * @Created by zengzg
 */
@Component
public class RabbitMQLogic implements ApplicationRunner {

    /**
     * 此处使用RabbitTemplate
     */
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQLogic(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public static final String EXCHANGE_A = "gang";
    public static final String ROUTINGKEY_A = "gang";

    public void sendMsg(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(EXCHANGE_A, ROUTINGKEY_A, content, correlationId);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        sendMsg("that's ok");
    }
}
