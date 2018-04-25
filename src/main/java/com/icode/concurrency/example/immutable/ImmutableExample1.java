package com.icode.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.icode.concurrency.annoations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@NotThreadSafe
public class ImmutableExample1 {

    private static final Logger logger = LoggerFactory.getLogger(ImmutableExample1.class);

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }

    public static void main(String[] args) {
        map.put(7, 8);

        logger.info(map.get(7).toString());
    }

    private void test(final int a){
//        a = 1;
    }
}
