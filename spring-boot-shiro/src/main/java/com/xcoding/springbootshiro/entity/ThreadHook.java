package com.xcoding.springbootshiro.entity;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

public class ThreadHook {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                try{
                    System.out.println("threadhook 1 is running.");
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("threadhook 1 is exit");
            }
        });
        //可定义多个钩子线程
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                try{
                    System.out.println("threadhook 2 is running.");
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("threadhook 2 is exit");
            }
        });
        System.out.println("threadhook is exit stoping.test");
    }
}
