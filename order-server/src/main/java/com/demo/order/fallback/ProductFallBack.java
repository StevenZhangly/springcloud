package com.demo.order.fallback;

import com.demo.order.service.ProductClient;
import org.springframework.stereotype.Component;

/**
 * @ClassName ProductFallBack
 * @Description: TODO
 * @Author zly
 * @Date 2020/5/8
 **/
@Component
public class ProductFallBack implements ProductClient {

    @Override
    public String findById(int id) {
        System.out.println("商品:"+id+",查询失败,开启断路器");
        return null;
    }
}
