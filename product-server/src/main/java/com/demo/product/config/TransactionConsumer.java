package com.demo.product.config;

import com.demo.product.listener.MQConsumeMsgListenerProcessor;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName TransactionConsumer
 * @Description: TODO
 * @Author zly
 * @Date 2021/4/14
 **/
@Configuration
@ConfigurationProperties(prefix = "rocketmq.producer")
public class TransactionConsumer {

    public static final Logger logger = LoggerFactory.getLogger(TransactionConsumer.class);

    @Autowired
    private MQConsumeMsgListenerProcessor mqConsumeMsgListenerProcessor;

    private String groupName;
    private String namesrvAddr;

    @Bean
    @ConditionalOnProperty(prefix = "rocketmq.producer", value = "isOnOff", havingValue = "true")
    public DefaultMQPushConsumer defaultConsumer() throws MQClientException {
        logger.info("defaultConsumer 正在创建---------------------------------------");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.subscribe("TopicTransaction", "order");
        // 设置监听
        consumer.registerMessageListener(mqConsumeMsgListenerProcessor);
        consumer.start();
        logger.info("consumer 创建成功  namesrvAddr={}" ,namesrvAddr);
        return consumer;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }
}
