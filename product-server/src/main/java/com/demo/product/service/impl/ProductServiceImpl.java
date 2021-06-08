package com.demo.product.service.impl;

import com.demo.product.domain.Product;
import com.demo.product.handle.ProductHandle;
import com.demo.product.mapper.ProductMapper;
import com.demo.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProductServiceImpl
 * @Description: TODO
 * @Author zly
 * @Date 2020/4/26
 **/
@Service
public class ProductServiceImpl implements ProductService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductHandle productHandle;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> productList() {
        return productMapper.queryList();
    }

    @Override
    public Product findById(int id) {
        logger.info("查询商品:{}信息", id);
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addProduct(Product product) {
        productMapper.insertSelective(product);
    }
}
