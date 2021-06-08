package com.demo.order.listener;

import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName MQConsumeMsgListenerProcessor
 * @Description: TODO
 * @Author zly
 * @Date 2020/12/25
 **/
@Component
public class MQConsumeMsgListenerProcessor implements MessageListenerConcurrently {

    public static final Logger LOGGER = LoggerFactory.getLogger(MQConsumeMsgListenerProcessor.class);

    /**
     * It is not recommend to throw exception,rather than returning ConsumeConcurrentlyStatus.RECONSUME_LATER if
     * consumption failure
     *
     * @param msgs    msgs.size() >= 1<br> DefaultMQPushConsumer.consumeMessageBatchMaxSize=1,you can modify here
     * @param context
     * @return The consume status
     */
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        if (CollectionUtils.isEmpty(msgs)) {
            LOGGER.info("MQ接收消息为空，直接返回成功");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        for (MessageExt messageExt : msgs){
            try {
                LOGGER.info("MQ接收到的消息为：" + messageExt.toString());
                String topic = messageExt.getTopic();
                String tags = messageExt.getTags();
                String body = new String(messageExt.getBody(), "utf-8");
                LOGGER.info("MQ消息topic={}, tags={}, 消息内容={}", topic,tags,body);
                int count = messageExt.getReconsumeTimes();
                LOGGER.info("当前消费重试次数为 = {}", count);
                if (count >= 2) {
                    LOGGER.info("该消息已经重试3次,保存数据库。topic={},tags={},msg={}", topic, tags, body);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            } catch (Exception e) {
                LOGGER.error("获取MQ消息内容异常",e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        // TODO 处理业务逻辑
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

    }
}
