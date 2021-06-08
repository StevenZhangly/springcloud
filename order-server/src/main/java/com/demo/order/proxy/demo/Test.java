package com.demo.order.proxy.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName Test
 * @Description: TODO
 * @Author zly
 * @Date 2020/10/15
 **/
public class Test {


    public static void main(String[] args) {

        MyPerson zly = new MyPerson();

        PersonInterface personProxy = (PersonInterface) Proxy.newProxyInstance(zly.getClass().getClassLoader(), zly.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before");
                method.invoke(zly, args);
                System.out.println("after");
                return null;
            }
        });

        personProxy.saySomething("hello");
    }
}
