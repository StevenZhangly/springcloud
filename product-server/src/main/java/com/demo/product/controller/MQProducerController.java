package com.demo.product.controller;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName MQProducerController
 * @Description: TODO
 * @Author zly
 * @Date 2020/12/25
 **/
@RestController
@RequestMapping("/mqProducer")
public class MQProducerController {

    public static final Logger LOGGER = LoggerFactory.getLogger(MQProducerController.class);

    @Autowired
    DefaultMQProducer defaultMQProducer;

    @GetMapping("/send")
    public String send(String msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        LOGGER.info("发送MQ消息内容：" + msg);
        Message message = new Message("TestTopic", "TestTag", msg.getBytes());
        SendResult result = defaultMQProducer.send(message);
        LOGGER.info("消息发送响应：" + result.toString());

        Map<String, String> map = new ConcurrentHashMap<>();
        map.putIfAbsent("", "");
        return result.getMsgId();
    }


}
