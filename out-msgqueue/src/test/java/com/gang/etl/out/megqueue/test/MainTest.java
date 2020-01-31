package com.gang.etl.out.megqueue.test;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @Classname MainTest
 * @Description TODO
 * @Date 2020/1/29 22:41
 * @Created by zengzg
 */
//@SpringBootTest
public class MainTest {

    private static final Logger log = LoggerFactory.getLogger(MainTest.class);

    public static final String QUEUE_A = "QUEUE_A";

    /**
     * 此处对 A 队列进行监听
     *
     * @param message
     * @param channel
     */
    @RabbitListener(queues = {QUEUE_A})
    public void listenerAutoAck(Message message, Channel channel) {
        // TODO 如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("[QUEUE_A listenerAutoAck 监听的消息] - [{}]", message);
            // TODO 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // TODO 处理失败,重新压入MQdefaultBookQueue
                channel.basicRecover();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
