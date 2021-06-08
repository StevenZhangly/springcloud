package com.demo.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.demo.order.domain.Order;
import com.demo.order.domain.ProductOrder;
import com.demo.order.mq.MQService;
import com.demo.order.service.ProductClient;
import com.demo.order.service.ProductOrderService;
import com.demo.order.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang.StringUtils;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

/**
 * @ClassName ProductOrderServiceImpl
 * @Description: TODO
 * @Author zly
 * @Date 2020/4/26
 **/
@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private MQService mqService;

    @Override
    public ProductOrder save(int userId, int productId) {
        logger.info("下单接口，入参:{},{}", userId, productId);

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

    @Override
    public void order(int productId, int saleCount){
        Message message = new Message();
        Order order = new Order();
        order.setProductId(productId);
        order.setSaleCount(saleCount);
        message.setTopic("TopicTransaction");
        message.setTags("order");
        message.setBody(JSON.toJSONString(order).getBytes());
        mqService.sendMessage(message);
    }

}
