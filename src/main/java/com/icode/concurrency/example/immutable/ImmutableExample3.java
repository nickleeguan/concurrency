package com.icode.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.icode.concurrency.annoations.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 不可变参数
 */
@ThreadSafe
public class ImmutableExample3 {

    private static final Logger logger = LoggerFactory.getLogger(ImmutableExample3.class);

    private final static List<Integer> list = ImmutableList.of(1, 2, 3);

    private final static Set<Integer> set = ImmutableSet.copyOf(list);

    private final static Map<Integer, Integer> map = ImmutableMap.of(1,2,3,4,5,6);

    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder()
            .put(1, 2).put(3, 4).put(5, 6).build();

    public static void main(String[] args) {
        list.add(7);//报错异常，不允许修改
        set.add(7);//同上
    }
}
