package com.demo.user.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @ClassName DataSourceAop
 * @Description: TODO
 * @Author zly
 * @Date 2021/10/22
 **/
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("execution(* com.demo.user.service..*.find*(..))")
    public void readPointcut(){

    }

    @Pointcut("execution(* com.demo.user.service..*.save*(..))")
    public void writePointcut(){

    }

    @Before("readPointcut()")
    public void read(){
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write(){
        DBContextHolder.master();
    }
}
