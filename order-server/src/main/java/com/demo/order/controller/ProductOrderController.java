package com.demo.order.controller;

import com.demo.order.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ProductOrderController
 * @Description: TODO
 * @Author zly
 * @Date 2020/4/26
 **/
@RestController
@RequestMapping("/api/v1/order")
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;

    @GetMapping("/save")
    @ResponseBody
    public Object save(int userId, int productId){
        return productOrderService.save(userId, productId);
    }

}
