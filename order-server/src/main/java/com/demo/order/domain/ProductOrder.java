package com.demo.order.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ProductOrder
 * @Description: TODO
 * @Author zly
 * @Date 2020/4/26
 **/
public class ProductOrder implements Serializable {

    private int id;

    //商品名称
    private String productName;

    //商品价格
    private int price;

    //创建时间
    private Date createTime;

    //订单号
    private String tradeNo;

    //用户id
    private int userId;

    //用户名称
    private String userName;

    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
