package com.xcoding.springbootshiro.entity;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class BooleanLockTest {
    private final Lock lock = new BooleanLock();
    public void syncMethod() {

        try{
            lock.lock();
            System.out.println(Thread.currentThread()+" get the lock");
            TimeUnit.SECONDS.sleep(10);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception{
        BooleanLockTest blt = new BooleanLockTest();
//        IntStream.range(0,10).mapToObj(i->new Thread(blt::syncMethod)).forEach(Thread::start);
        new Thread(blt::syncMethod,"T1").start();
        TimeUnit.SECONDS.sleep(2);
        Thread t2 = new Thread(blt::syncMethod,"T2");
        t2.start();
        TimeUnit.SECONDS.sleep(2);
        t2.interrupt();//可以实现synchronized不能实现的中断
    }
}
