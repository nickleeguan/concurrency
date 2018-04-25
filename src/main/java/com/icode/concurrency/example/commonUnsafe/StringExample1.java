package com.icode.concurrency.example.commonUnsafe;

import com.icode.concurrency.ConcurrencyTest;
import com.icode.concurrency.annoations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@NotThreadSafe
public class StringExample1 {
    private static final Logger logger = LoggerFactory.getLogger(ConcurrencyTest.class);

    //请求总数
    public static int clientTotal = 5000;

    //最大并发数
    public static int threadTotal = 200;

    public static StringBuilder stringBuilder = new StringBuilder();

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
                update();
                semaphore.release();

                countDownLatch.countDown();
            });
        }
        //执行下面语句前必须减到0
        countDownLatch.await();
        service.shutdown();
        logger.info("stringBuilder:{}", stringBuilder.length());
    }

    private static void update(){
        stringBuilder.append("1");
    }
}
