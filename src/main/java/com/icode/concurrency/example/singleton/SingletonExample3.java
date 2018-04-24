package com.icode.concurrency.example.singleton;

import com.icode.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 线程安全,有性能开销
 */
@ThreadSafe
public class SingletonExample3 {
    //私有构造函数
    private SingletonExample3(){

    }
    //单例对象
    private static SingletonExample3 instance = null;

    //静态的工厂方法
    public synchronized static SingletonExample3 getInstance(){
        if (instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
