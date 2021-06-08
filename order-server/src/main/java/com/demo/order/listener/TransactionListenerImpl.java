package com.demo.order.listener;

import com.alibaba.fastjson.JSON;
import com.demo.order.domain.Order;
import com.demo.order.handle.OrderHandle;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName TransactionListenerImpl
 * @Description: 消息事务监听器
 * 重写两个方法，1.执行本地事务 2本地事务回查
 * @Author zly
 * @Date 2021/4/9
 **/
@Component
public class TransactionListenerImpl implements TransactionListener {

    public static final Logger LOGGER = LoggerFactory.getLogger(TransactionListenerImpl.class);

    @Autowired
    private OrderHandle orderHandle;

    /**
     * When send transactional prepare(half) message succeed, this method will be invoked to execute local transaction.
     *
     * @param msg Half(prepare) message
     * @param arg Custom business parameter
     * @return Transaction state
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        LOGGER.info("开始执行本地事务消息:{}", JSON.toJSONString(msg));
        Order order = JSON.parseObject(msg.getBody(), Order.class);
        LocalTransactionState state = null;
        boolean result = false;
        try {
            result = orderHandle.order(order.getProductId(), order.getSaleCount(), msg.getTransactionId());
        } catch (Exception e) {
            LOGGER.warn("下单异常", e);
            //catch异常，将事务状态设置为UNKNOW,通过broker的事务回查来确定事务提交状态
            state = LocalTransactionState.UNKNOW;
            return state;
        }
        if(result){
            //提交事务
            state = LocalTransactionState.COMMIT_MESSAGE;
        } else {
            //回滚事务
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        }
        LOGGER.info("本地事务执行状态:{}", state);
        return state;
    }

    /**
     * When no response to prepare(half) message. broker will send check message to check the transaction status, and this
     * method will be invoked to get local transaction status.
     *
     * @param msg Check message
     * @return Transaction state
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        LocalTransactionState state = null;
        boolean result = false;
        try {
            result = orderHandle.checkOrderStatus(msg.getTransactionId());
            if(result){
                state = LocalTransactionState.COMMIT_MESSAGE;
            } else {
                state = LocalTransactionState.ROLLBACK_MESSAGE;
            }
        } catch (Exception e) {
            state = LocalTransactionState.UNKNOW;
        }
        LOGGER.info("检查本地事务:{}, 执行状态:{}", msg.getTransactionId(), state);
        return state;
    }
}
