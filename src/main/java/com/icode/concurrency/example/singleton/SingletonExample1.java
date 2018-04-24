package com.icode.concurrency.example.singleton;

import com.icode.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式
 * 在对象第一次调用的时候进行创建
 */
@NotThreadSafe
public class SingletonExample1 {
    //私有构造函数
    private SingletonExample1(){

    }
    //单例对象
    private static SingletonExample1 instance = null;

    //静态的工厂方法
    public static SingletonExample1 getInstance(){
        if (instance == null){
            instance = new SingletonExample1();
        }

        return instance;
    }
}
