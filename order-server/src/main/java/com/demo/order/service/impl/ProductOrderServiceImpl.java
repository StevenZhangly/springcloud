package com.demo.order.service.impl;

import com.demo.order.domain.ProductOrder;
import com.demo.order.service.ProductClient;
import com.demo.order.service.ProductOrderService;
import com.demo.order.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName ProductOrderServiceImpl
 * @Description: TODO
 * @Author zly
 * @Date 2020/4/26
 **/
@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ProductClient productClient;

    @Override
    public ProductOrder save(int userId, int productId) {

        //方式一 使用Ribbon的RestTemplate方式
        //Map<String, Object> map = restTemplate.getForObject("http://product-server/api/v1/product/find?id="+productId, Map.class);

        //方式二 使用Ribbon的LoadBalancerClient方式
        /*ServiceInstance instance = loadBalancerClient.choose("product-server");
        String url = String.format("http://%s:%s/api/v1/product/find?id=%s", instance.getHost(), instance.getPort(), productId);
        Map<String, Object> map = new RestTemplate().getForObject(url, Map.class);

        System.out.println(map);*/

        //方式三 使用Feign
        String response = productClient.findById(productId);
        ProductOrder productOrder = new ProductOrder();
        if(StringUtils.isNotBlank(response)){
            JsonNode jsonNode = JsonUtils.str2JsonNode(response);
            productOrder.setCreateTime(new Date());
            productOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));
            productOrder.setTradeNo(UUID.randomUUID().toString());
            productOrder.setUserId(userId);
            productOrder.setUserName("zly"+userId);
            productOrder.setProductName(jsonNode.get("name").toString());
            productOrder.setRemark(jsonNode.get("remark").toString());
        }
        return productOrder;
    }
}
