package com.chand.singleton;

/**
 * 枚举式单例模式
 *
 * @author Chand
 * @Date: 2019-07-12 17:03:04
 **/
public enum EnumSingleton {

    INSTANCE;

    public void doSomething() {
        System.out.println("doSomething");
    }
}
