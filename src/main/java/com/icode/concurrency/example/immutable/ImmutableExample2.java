package com.icode.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.icode.concurrency.annoations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

@ThreadSafe
public class ImmutableExample2 {

    private static final Logger logger = LoggerFactory.getLogger(ImmutableExample2.class);

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(7, 8);//报错，不允许修改

        logger.info(map.get(7).toString());
    }

}
