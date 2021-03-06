package com.demo.order.controller;

import com.demo.order.domain.ProductOrder;
import com.demo.order.service.ProductOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ProductOrderController
 * @Description: TODO
 * @Author zly
 * @Date 2020/4/26
 **/
@RestController
@RequestMapping("/api/v1/order")
public class ProductOrderController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProductOrderController.class);

    @Autowired
    private ProductOrderService productOrderService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/save")
    @ResponseBody
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public Object save(int userId, int productId, HttpServletRequest request){
        String token = request.getHeader("token");
        String cookie = request.getHeader("Cookie");
        System.out.println("token:"+token);
        System.out.println("cookie:"+cookie);

        ProductOrder data = productOrderService.save(userId, productId);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", data);
        return map;
    }

    public Object saveOrderFail(int userId, int productId, HttpServletRequest request){
        String saveOrderKey = "save_order_" + productId;
        String sendValue = redisTemplate.opsForValue().get(saveOrderKey);
        //监控报警,异步通知
        new Thread(()->{
            if(StringUtils.isBlank(sendValue)){
                System.out.println("用户下单失败，请查找原因!!!");
                redisTemplate.opsForValue().set(saveOrderKey, "save_order_fail", 20, TimeUnit.SECONDS);
                //发送短信或者邮件通知

            } else {
                System.out.println("20s内不发送通知");
            }
        }).start();

        Map<String, Object> map = new HashMap<>();
        map.put("code", -1);
        map.put("msg", "抢购人数太多，您被挤出来了，请稍后重试");
        return map;
    }


    @PostMapping("/save")
    public void order(Integer productId, Integer saleCount){
        LOGGER.info("下单请求，入参:{},{}", productId, saleCount);
        productOrderService.order(productId, saleCount);
    }
}
