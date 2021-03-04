package com.xcoding.springbootshiro.entity;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface Lock {
    void lock() throws InterruptedException;//永远阻塞
    void lock(long mills) throws InterruptedException, TimeoutException;//增加超时功能
    void unlock();//进行锁的释放
    List<Thread> getBlockedThreads();//获取当前有那些线程被阻塞
}
