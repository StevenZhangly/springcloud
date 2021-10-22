package com.demo.user.config;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName DBContextHolder
 * @Description: TODO
 * @Author zly
 * @Date 2021/10/22
 **/
@Slf4j
public class DBContextHolder {

    public static ThreadLocal<DBTypeEnum> threadLocal = new ThreadLocal<>();

    public static AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBTypeEnum dbTypeEnum){
        threadLocal.set(dbTypeEnum);
    }

    public static DBTypeEnum get(){
        return threadLocal.get();
    }

    public static void master(){
        set(DBTypeEnum.MASTER);
        log.info("切换到master");
    }

    public static void slave(){
        int index = counter.incrementAndGet() % 2;
        if(counter.get() > 9999){
            counter.set(-1);
        }
        if(index == 0){
            set(DBTypeEnum.SLAVE1);
            log.info("切换到slave1");
        } else{
            set(DBTypeEnum.SLAVE2);
            log.info("切换到slave2");
        }
    }
}
