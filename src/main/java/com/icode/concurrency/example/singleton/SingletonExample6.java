package com.icode.concurrency.example.singleton;

import com.icode.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 在类装载的时候进行创建
 */
@ThreadSafe
public class SingletonExample6 {

    //单例对象
    private static SingletonExample6 instance = null;

    //私有构造函数
    private SingletonExample6(){

    }
    static {
        instance = new SingletonExample6();
    }

    //静态的工厂方法
    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
