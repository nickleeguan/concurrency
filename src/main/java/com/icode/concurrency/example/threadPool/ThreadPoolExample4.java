package com.icode.concurrency.example.threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample4 {

    private final static Logger logger = LoggerFactory.getLogger(ThreadPoolExample4.class);

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

//        service.schedule(new Runnable() {
//            @Override
//            public void run() {
//                logger.warn("schedule run");
//            }
//        }, 3, TimeUnit.SECONDS);

        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                logger.warn("fix rate run");
            }
        }, 1, 3, TimeUnit.SECONDS);
//        service.shutdown();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logger.warn("timer run");
            }
        }, new Date(), 5*1000);
    }
}
