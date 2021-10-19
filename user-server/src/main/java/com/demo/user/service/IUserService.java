package com.demo.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.user.entity.User;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zly
 * @since 2021-06-25
 */
public interface IUserService extends IService<User> {

    /**
     * 保存用户信息
     * @param entity
     * @return
     */
    @Override
    boolean save(User entity);

    /**
     * 查询全部用户信息
     * @return
     */
    List<User> getUserList();
}
