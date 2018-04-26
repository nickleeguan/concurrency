package com.icode.concurrency.example.syncContainer;

import com.icode.concurrency.annoations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class VectorExample3 {
    private static final Logger log = LoggerFactory.getLogger(VectorExample3.class);

    public static void main(String[] args) throws Exception {
        Vector<Integer> vector = new Vector<>();

        vector.add(1);
        vector.add(2);
        vector.add(3);

//        test1(vector);
//        test2(vector);
        test3(vector);
    }

    private static void test1(Vector<Integer> v1){
        for (Integer i : v1){
            if (i.equals(3)){
                v1.remove(i);
            }
        }
    }

    private static void test2(Vector<Integer> v1){
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            if (i.equals(3)){
                v1.remove(i);
            }
        }
    }

    private static void test3(Vector<Integer> v1){
        for (int i = 0; i < v1.size(); i++) {
            if (v1.get(i).equals(3)){
                v1.remove(i);
            }
        }
    }
}
