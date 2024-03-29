package com.demo.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.user.entity.User;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zly
 * @since 2021-06-25
 */
public interface UserMapper extends BaseMapper<User> {

    Integer insertUser(User model);

    void saveUser(User model);

    List<User> findByName(String name);

    User getUserById(int id);
}
