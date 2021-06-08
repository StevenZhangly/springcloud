package com.demo.product.listener;

import com.alibaba.fastjson.JSON;
import com.demo.product.handle.ProductHandle;
import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MQConsumeMsgListenerProcessor
 * @Description: TODO
 * @Author zly
 * @Date 2021/4/14
 **/
@Component
public class MQConsumeMsgListenerProcessor implements MessageListenerConcurrently {

    public static final Logger LOGGER = LoggerFactory.getLogger(MQConsumeMsgListenerProcessor.class);

    @Autowired
    private ProductHandle productHandle;

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
        for(MessageExt messageExt : msgs){
            LOGGER.info("MQ接收到的消息为：" + messageExt.toString());
            int reconsumeTimes = messageExt.getReconsumeTimes();
            if(reconsumeTimes > 2){
                LOGGER.warn("超过最大重试次数");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            String body = new String(messageExt.getBody());
            try {
                Map<String, Object> map = JSON.parseObject(body, Map.class);
                productHandle.updateProductStore((int)map.get("productId"), (int)map.get("saleCount"));
            } catch (Exception e) {
                LOGGER.warn("更新商品库存异常", e);
                //重新消费，注意幂等
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
