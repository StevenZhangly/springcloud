package com.demo.order.mq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName MQService
 * @Description: TODO
 * @Author zly
 * @Date 2021/4/14
 **/
@Service
public class MQService {

    public static final Logger LOGGER = LoggerFactory.getLogger(MQService.class);

    @Autowired
    private TransactionMQProducer transactionMQProducer;

    public void sendMessage(Message msg){
        try {
            LOGGER.info("发送MQ消息请求:{}", msg);
            TransactionSendResult result = transactionMQProducer.sendMessageInTransaction(msg, null);
            LOGGER.info("发送MQ消息响应:{}", result);
        } catch (MQClientException e) {
            LOGGER.warn("发送MQ消息异常", e);
        }
    }
}
