package com.demo.order.proxy.demo;

/**
 * @ClassName MyPerson
 * @Description: TODO
 * @Author zly
 * @Date 2020/10/15
 **/
public class MyPerson implements PersonInterface{


    @Override
    public void doSomething() {
        System.out.println("do something");
    }

    @Override
    public void saySomething(String word) {
        System.out.println("say:" + word);
    }

    private void test(){
        System.out.println("private test");
    }
}
