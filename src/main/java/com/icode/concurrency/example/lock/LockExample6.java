package com.icode.concurrency.example.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample6 {

    private final static Logger logger = LoggerFactory.getLogger(LockExample6.class);

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();
                logger.info("wait signal");//1
                condition.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            logger.info("get signal");//4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            logger.info("get lock");//2
            try {
                Thread.sleep(3000);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            condition.signalAll();
            logger.info("send signal ~~~");//3
            reentrantLock.unlock();
        }).start();
    }
}
