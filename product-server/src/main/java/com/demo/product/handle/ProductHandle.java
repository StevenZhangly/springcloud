package com.demo.product.handle;

import com.demo.product.domain.Product;
import com.demo.product.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @ClassName ProductHandle
 * @Description: TODO
 * @Author zly
 * @Date 2020/5/7
 **/
@Component
public class ProductHandle {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProductHandle.class);

    @Autowired
    private ProductMapper productMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateProductStore(int id, int saleCount){
        LOGGER.info("更新库存, 入参:{},{}", id, saleCount);
        Product product = productMapper.selectByPrimaryKey(id);
        if(product.getStore() < saleCount){
            LOGGER.warn("商品库存不足,当前库存为:{}", product.getStore());
            throw new RuntimeException("商品库存不足");
        }
        Product updateProduct = new Product();
        updateProduct.setId(id);
        updateProduct.setStore(product.getStore()-saleCount);
        updateProduct.setUpdateTime(new Date());
        productMapper.updateByPrimaryKeySelective(updateProduct);
    }

}
