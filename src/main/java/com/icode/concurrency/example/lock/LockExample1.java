package com.icode.concurrency.example.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample1 {
    private static final Logger logger = LoggerFactory.getLogger(LockExample1.class);


    //请求总数
    public static int clientTotal = 5000;

    //最大并发数
    public static int threadTotal = 200;

    public static int count = 0;

    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception{
        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0;  i < clientTotal; i++){
            service.execute(() -> {
                try{
                    semaphore.acquire();
                }catch (InterruptedException e){
                    logger.error("exception", e);
                }
                add();
                semaphore.release();

                countDownLatch.countDown();
            });
        }
        //执行下面语句前必须减到0
        countDownLatch.await();
        service.shutdown();
        logger.info("count:{}", count);
    }

    private static void add(){
        lock.lock();
        try{
            count++;
        }finally {
            lock.unlock();
        }
    }
}
