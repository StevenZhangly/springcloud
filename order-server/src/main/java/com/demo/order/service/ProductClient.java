package com.demo.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-server")
public interface ProductClient {

    @GetMapping(value = "/api/v1/product/find")
    String findById(@RequestParam(value = "id") int id);
}
