package com.icode.concurrency.example.commonUnsafe;

import com.icode.concurrency.annoations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@NotThreadSafe
public class DateFormatExample1 {

    private static final Logger logger = LoggerFactory.getLogger(DateFormatExample1.class);

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    //请求总数
    public static int clientTotal = 5000;

    //最大并发数
    public static int threadTotal = 200;


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
    }

    private static void update(){
        try {
            simpleDateFormat.parse("20180208");
        }catch (Exception e){
            logger.error("parse exception:{}", e);
        }

    }
}
