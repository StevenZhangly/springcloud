package com.demo.product.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Product
 * @Description: TODO
 * @Author zly
 * @Date 2020/4/26
 **/
public class Product implements Serializable {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 金额
     */
    private Long price;

    /**
     * 库存
     */
    private Integer store;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 获取 主键ID
     *
     * @return 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 名称
     *
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取 金额
     *
     * @return 金额
     */
    public Long getPrice() {
        return price;
    }

    /**
     * 设置 金额
     *
     * @param price 金额
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * 获取 库存
     *
     * @return 库存
     */
    public Integer getStore() {
        return store;
    }

    /**
     * 设置 库存
     *
     * @param store 库存
     */
    public void setStore(Integer store) {
        this.store = store;
    }

    /**
     * 获取 备注
     *
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取 创建时间
     *
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 更新时间
     *
     * @return 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        sb.append(", store=").append(store);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
