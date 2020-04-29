package com.demo.product.controller;

import com.demo.product.domain.Product;
import com.demo.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("list")
    @ResponseBody
    public Object list(){
     return productService.productList();
    }

    @GetMapping("find")
    @ResponseBody
    public Object findById(int id){
        Product result = productService.findById(id);
        result.setRemark("");
        result.setRemark("get result from :" + port);
        return result;
    }
}
