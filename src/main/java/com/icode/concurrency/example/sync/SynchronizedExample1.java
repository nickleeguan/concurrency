package com.icode.concurrency.example.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 以调用对象为锁线程安全
 */
public class SynchronizedExample1 {


    private static final Logger logger = LoggerFactory.getLogger(SynchronizedExample1.class);

    //修饰代码块
    public void test1(int j){
        synchronized (this){
            for (int i = 0; i < 10; i++) {
                logger.info("test1 {} - {}", j, i);
            }
        }
    }

    //修饰一个方法
    public synchronized void test2(int j){
        for (int i = 0; i < 10; i++) {
            logger.info("test2 {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            example1.test2(1);
        });
        service.execute(() -> {
            example2.test2(2);
        });
    }
}
