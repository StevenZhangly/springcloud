package com.demo.order.service;

import com.demo.order.fallback.ProductFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 */
@FeignClient(name = "product-server", fallback = ProductFallBack.class)
public interface ProductClient {

    /**
     * 根据id查询商品信息
     * @param id 商品id
     * @return 商品信息json字符串
     */
    @GetMapping(value = "/api/v1/product/find")
    String findById(@RequestParam(value = "id") int id);
}
