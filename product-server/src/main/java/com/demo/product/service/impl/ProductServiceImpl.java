package com.demo.product.service.impl;

import com.demo.product.domain.Product;
import com.demo.product.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @ClassName ProductServiceImpl
 * @Description: TODO
 * @Author zly
 * @Date 2020/4/26
 **/
@Service
public class ProductServiceImpl implements ProductService {

    private static Map<Integer, Product> productMap = new HashMap<>();

    static{
        Product p1 = new Product(1, "nike", 200, 10);
        Product p2 = new Product(2, "adidas", 300, 5);
        Product p3 = new Product(3, "iphone", 10000, 100);
        productMap.put(1, p1);
        productMap.put(2, p2);
        productMap.put(3, p3);
    }


    @Override
    public List<Product> productList() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public Product findById(int id) {
        return productMap.get(id);
    }
}
