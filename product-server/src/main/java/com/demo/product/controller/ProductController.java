package com.demo.product.controller;

import com.demo.product.domain.Product;
import com.demo.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ClassName ProductController
 * @Description: TODO
 * @Author zly
 * @Date 2020/4/26
 **/
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/list")
    @ResponseBody
    public Object list(){
     return productService.productList();
    }

    @GetMapping("/find")
    @ResponseBody
    public Object findById(int id){
        /*try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Product result = productService.findById(id);
        result.setRemark("");
        result.setRemark("get result from :" + port);
        return result;
    }

    @PostMapping("/save")
    public void saveProduct(@RequestBody Product product){
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        productService.addProduct(product);
    }
}
