package com.icode.concurrency.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample1 {

    private static final Logger logger = LoggerFactory.getLogger(CyclicBarrierExample1.class);

    private static CyclicBarrier barrier = new CyclicBarrier(5);

    public static void main(String[] args)  throws  Exception{
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            service.execute(() ->{
                try {
                    race(threadNum);
                }catch (Exception e){
                    logger.info("exception", e);
                }

            });
        }
        service.shutdown();
    }

    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        logger.info("{} is ready", threadNum);
        barrier.await();
        logger.info("{} continue", threadNum);
    }
}
