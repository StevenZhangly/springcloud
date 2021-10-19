package com.demo.user.service.handle;

import com.demo.user.entity.User;
import com.demo.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserHandler
 * @Description: TODO
 * @Author zly
 * @Date 2021/7/15
 **/
@Component
public class UserHandler {

    @Autowired
    private UserMapper userMapper;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 2, rollbackFor = Exception.class)
    public void insertUser(User user){
        userMapper.insert(user);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
