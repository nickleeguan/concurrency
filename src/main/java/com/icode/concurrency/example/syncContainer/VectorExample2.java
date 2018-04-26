package com.icode.concurrency.example.syncContainer;

import com.icode.concurrency.annoations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Vector;

@NotThreadSafe
public class VectorExample2 {

    private static final Logger logger = LoggerFactory.getLogger(VectorExample2.class);

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }
}
