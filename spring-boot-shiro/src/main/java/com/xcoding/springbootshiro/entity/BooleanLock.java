package com.xcoding.springbootshiro.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BooleanLock implements Lock {
    private Thread currentThread;//当前拥有锁的线程
    private boolean locked = false;//false代表该锁没有被获得，true代表该锁被某个线程获得，此线程就是currentThread
    private final List<Thread> blockedList = new ArrayList<>();//存储那些线程在获取当前线程时进入阻塞状态

    @Override
    public void lock() throws InterruptedException {
        synchronized (this){
            while(locked){
                blockedList.add(Thread.currentThread());//当前锁被其他线程获得，将线程加入阻塞队列中
                this.wait();//释放对this的所有权
            }
            blockedList.remove(Thread.currentThread());//如果当前没有其他线程获得锁，则从阻塞队列中删除自己
            this.locked = true;
            this.currentThread = Thread.currentThread();//记录获得锁的线程
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this){
            if(mills <= 0 ){
                this.lock();
            }else{
                long remainingMills = mills;
                long endMills = System.currentTimeMillis() + remainingMills;
                while(locked){
                    if(remainingMills<=0){
                        throw new TimeoutException("can not get the lock during "+mills);
                    }
                    if(!blockedList.contains(Thread.currentThread())){
                        blockedList.add(Thread.currentThread());
                    }
                    this.wait(remainingMills);
                    remainingMills = endMills - System.currentTimeMillis();
                }
                blockedList.remove(Thread.currentThread());
                this.locked = true;
                this.currentThread = Thread.currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this){
            if(this.currentThread == Thread.currentThread()){//判断线程是否为获得锁的线程
                this.locked = false;
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
