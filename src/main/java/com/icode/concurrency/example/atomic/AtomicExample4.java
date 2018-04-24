package com.icode.concurrency.example.atomic;

import com.icode.concurrency.annoations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class AtomicExample4 {

    private static final Logger logger = LoggerFactory.getLogger(AtomicExample4.class);

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 2);
        count.compareAndSet(0, 1);
        count.compareAndSet(1, 3);
        count.compareAndSet(2, 4);
        count.compareAndSet(3, 5);

        logger.info("count:{}", count.get());
    }
}
