package com.demo.user.service;

import com.alibaba.fastjson.JSON;
import com.demo.user.entity.User;
import com.demo.user.msmapper.MsUserMapper;
import io.shardingsphere.api.HintManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName ShardingJdbcMaterSlaveTest
 * @Description: TODO
 * @Author zly
 * @Date 2021/10/26
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJdbcMaterSlaveTest {

    @Autowired
    private MsUserMapper msUserMapper;

    @Test
    public void saveUser(){
        for (int i = 200; i <= 205; i++) {
            User user = new User();
            user.setId((long)i);
            user.setName("zly"+i);
            user.setAge(i);
            msUserMapper.saveUser(user);
        }
    }

    @Test
    public void findUser(){
        for (int i = 200; i <= 205; i++) {
            if(i== 203){
                //强制路由主库（解决读延迟）
                HintManager.getInstance().setMasterRouteOnly();
            }
            List<User> users = msUserMapper.findByName("zly"+i);
            System.out.println(JSON.toJSONString(users));
        }
    }

}
