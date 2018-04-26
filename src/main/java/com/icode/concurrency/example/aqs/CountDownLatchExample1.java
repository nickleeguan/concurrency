package com.icode.concurrency.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample1 {

    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchExample1.class);
    private static final int threadCount = 200;

    public static void main(String[] args)  throws Exception{
        ExecutorService service = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            service.execute(() -> {
                try {
                    test(threadNum);
                }catch (Exception e){
                    logger.error("exception", e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();//确保线程执行完
        logger.info("finish");
        service.shutdown();
    }

    private static void test(int threadNum) throws Exception{
        Thread.sleep(100);
        logger.info("{}", threadNum);
        Thread.sleep(100);
    }
}
