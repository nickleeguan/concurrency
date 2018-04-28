package com.icode.concurrency.example.threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample1 {

    private final static Logger logger = LoggerFactory.getLogger(ThreadPoolExample1.class);

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    logger.info("task:{}", index);
                }
            });
        }
        service.shutdown();
    }
}
