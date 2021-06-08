package com.demo.order.handle;

import com.demo.order.domain.Order;
import com.demo.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName OrderHandle
 * @Description: TODO
 * @Author zly
 * @Date 2021/4/9
 **/
@Component
public class OrderHandle {

    @Autowired
    private OrderMapper orderMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean order(int productId, int saleCount, String transactionId){
        Order order = new Order();
        order.setProductId(productId);
        order.setSaleCount(saleCount);
        //记录消息事务transactionId 用于后续状态回查
        order.setTransactionId(transactionId);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        //模拟事务提交异常
        //int a = 1 / 0;
        int result = orderMapper.insertSelective(order);
        if(result != 1){
            return false;
        }
        return true;
    }

    public boolean checkOrderStatus(String transactionId){
        int result = orderMapper.selectOrderByTransactionId(transactionId);
        if(result > 0){
            return true;
        }
        return false;
    }
}
