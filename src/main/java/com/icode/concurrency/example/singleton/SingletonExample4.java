package com.icode.concurrency.example.singleton;

import com.icode.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式 ->双层同步锁单例模式
 * 线程安全,有性能开销
 */
@ThreadSafe
public class SingletonExample4 {
    //私有构造函数
    private SingletonExample4(){

    }

    //1. memory = allocate() 分配对象的内存空间
    //2. ctorInstance() 初始化对象
    //3. instance = memory 设置Instance指向刚分配的内存

    //jvm和cpu优化,发生里指令重排

    //1. memory = allocate() 分配对象的内存空间
    //3. instance = memory 设置Instance指向刚分配的内存
    //2. ctorInstance() 初始化对象

    //解决方法使用：volatile + 双重检测机制 -> 禁止指令重排

    //单例对象
    private volatile static SingletonExample4 instance = null;

    //静态的工厂方法
    public synchronized static SingletonExample4 getInstance(){
        if (instance == null){//双层检测机制 //B
            synchronized (SingletonExample4.class){//同步锁
                if (instance == null){
                    instance = new SingletonExample4(); //A - 3
                }
            }
        }
        return instance;
    }
}
