package com.demo.order.service;

import com.demo.order.domain.ProductOrder;

public interface ProductOrderService {

    /**
     * 下单接口
     * @param userId
     * @param productId
     * @return
     */
    ProductOrder save(int userId, int productId);


    void order(int productId, int saleCount);
}
