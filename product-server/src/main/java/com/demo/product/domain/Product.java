package com.demo.product.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName Product
 * @Description: TODO
 * @Author zly
 * @Date 2020/4/26
 **/
public class Product implements Serializable {

    private int id;

    //名称
    private String name;

    //价格(以分为单位)
    private int price;

    //库存
    private int store;

    private String remark;

    public Product(int id, String name, int price, int store) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
