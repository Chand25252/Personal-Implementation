package com.chand.singleton;

/**
 * 懒汉式单例模式
 *
 * @author Chand
 * @Date: 2019-07-12 13:56:07
 **/
public class LazySingleton {

    /**
     * 静态私有成员，不进行初始化
     */
    private static LazySingleton instance = null;

    /**
     * 构造方法私有化
     */
    private LazySingleton() {

    }

    /**
     * 只有第一次访问时, 才创建实例, 实现懒加载
     * 加同步锁解决线程安全问题
     *
     * @return 返回单例对象
     */
    public static synchronized LazySingleton getInstance() {
        if (null == instance) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
