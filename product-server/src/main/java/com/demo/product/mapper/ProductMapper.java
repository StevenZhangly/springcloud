package com.demo.product.mapper;


import com.demo.product.domain.Product;

import java.util.List;

public interface ProductMapper {
    /**
     *  根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *  新写入数据库记录
     *
     * @param record
     */
    int insert(Product record);

    /**
     *  动态字段,写入数据库记录
     *
     * @param record
     */
    int insertSelective(Product record);

    /**
     *  根据指定主键获取一条数据库记录
     *
     * @param id
     */
    Product selectByPrimaryKey(Integer id);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     *  根据主键来更新符合条件的数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(Product record);

    List<Product> queryList();
}