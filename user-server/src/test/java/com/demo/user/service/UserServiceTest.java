package com.demo.user.service;

import com.demo.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName UserServiceTest
 * @Description: TODO
 * @Author zly
 * @Date 2021/9/27
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void insertUser(){
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setId((long)i);
            user.setName("zly"+i);
            user.setAge(i);
            userService.save(user);
        }
    }
}
