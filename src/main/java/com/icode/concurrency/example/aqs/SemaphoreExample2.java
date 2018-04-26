package com.icode.concurrency.example.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample2 {

    private static final Logger logger = LoggerFactory.getLogger(SemaphoreExample2.class);
    private static final int threadCount = 200;

    public static void main(String[] args)  throws Exception{
        ExecutorService service = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            service.execute(() -> {
                try {
                    semaphore.acquire(3);//获取许可
                    test(threadNum);
                    semaphore.release(3);//释放许可
                }catch (Exception e){
                    logger.error("exception", e);
                }
            });
        }

        service.shutdown();
    }

    private static void test(int threadNum) throws Exception{
        logger.info("{}", threadNum);
        Thread.sleep(1000);
    }
}
