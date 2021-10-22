package com.demo.user.service;

import com.alibaba.fastjson.JSON;
import com.demo.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Test
    public void saveUser(){
        for (int i = 3; i <= 5; i++) {
            User user = new User();
            user.setId((long)i);
            user.setName("zly"+i);
            user.setAge(i);
            userService.saveUser(user);
        }
    }

    @Test
    public void findUser(){
        for (int i = 1; i <= 5; i++) {
            List<User> users = userService.findByName("zly"+i);
            System.out.println(JSON.toJSONString(users));
        }
    }
}
