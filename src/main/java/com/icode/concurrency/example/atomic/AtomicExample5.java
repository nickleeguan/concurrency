package com.icode.concurrency.example.atomic;

import com.icode.concurrency.annoations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@ThreadSafe
public class AtomicExample5 {

    private static final Logger logger = LoggerFactory.getLogger(AtomicExample5.class);

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");

    private volatile int count = 100;

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        AtomicExample5 example5 = new AtomicExample5();

        if (updater.compareAndSet(example5, 100, 120)){
            logger.info("update success 1, {}", example5.getCount());
        }

        if (updater.compareAndSet(example5, 100, 120)){
            logger.info("update success 2, {}", example5.getCount());
        }else {
            logger.info("update failed, {}", example5.getCount());
        }
    }
}
