package com.gang.etl.out.msgqueue.mq;

import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Classname RabbitMQConfig
 * @Description TODO
 * @Date 2020/1/29 21:34
 * @Created by zengzg
 */
@Configuration
public class RabbitMQConfig {

    private RabbitMQProperties rabbitMQProperties = new RabbitMQProperties();

    /**
     * @return
     */
    @Bean
    public Queue queueA() {
        return new Queue(rabbitMQProperties.getQueue(), true); //队列持久
    }

    /**
     * 创建 exchange 对象
     *
     * @return
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(rabbitMQProperties.getExchange());
    }

    /**
     * 将queue 指定的queue 进行板顶
     *
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(rabbitMQProperties.getRoutingKey());
    }

    @Configuration
    @PropertySource(value = "classpath:application-msgqueue.yml")
    @ConfigurationProperties(prefix = "gang.etl.mq.rabbit")
    private class RabbitMQProperties {

        private String queue;

        private String exchange;

        private String routingKey;

        public String getQueue() {
            return queue;
        }

        public void setQueue(String queue) {
            this.queue = queue;
        }

        public String getExchange() {
            return exchange;
        }

        public void setExchange(String exchange) {
            this.exchange = exchange;
        }

        public String getRoutingKey() {
            return routingKey;
        }

        public void setRoutingKey(String routingKey) {
            this.routingKey = routingKey;
        }
    }
}
