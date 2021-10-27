package com.demo.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.user.config.MasterRouteOnly;
import com.demo.user.entity.User;
import com.demo.user.mapper.UserMapper;
import com.demo.user.service.IUserService;
import com.demo.user.service.handle.UserHandler;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zly
 * @since 2021-06-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserHandler userHandler;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 查询全部用户信息
     *
     * @return
     */
    @Override
    public List<User> getUserList() {
        return baseMapper.selectList(Wrappers.lambdaQuery());
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public List<User> findByName(String name) {
        return userMapper.findByName(name);
    }


    @Override
    public int batchInsert(List<User> users) {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        for (User user : users) {
            userMapper.saveUser(user);
        }
        sqlSession.flushStatements();
        sqlSession.commit();
        return users.size();
    }

    @Override
    @MasterRouteOnly
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }

    @Override
    public boolean save(User entity) {
        //baseMapper.insert(entity);
        //userHandler.insertUser(entity);
        userMapper.insertUser(entity);
        return true;
    }
}
