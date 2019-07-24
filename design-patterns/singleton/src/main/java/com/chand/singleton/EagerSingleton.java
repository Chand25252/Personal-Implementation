package com.chand.singleton;

/**
 * 饿汉式单例模式
 *
 * @author Chand
 * @Date: 2019-07-12 10:32:05
 */
public class EagerSingleton {

    /**
     * 静态实例,类加载时创建
     */
    private static EagerSingleton instance = new EagerSingleton();

    /**
     * 构造方法私有化
     */
    private EagerSingleton() {
    }

    /**
     * 返回静态实例
     *
     * @return 返回单例对象
     */
    public static EagerSingleton getInstance() {
        return instance;
    }
}
