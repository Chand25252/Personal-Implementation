package com.chand.singleton;

/**
 * 静态内部类单例模式
 *
 * @author Chand
 * @Date: 2019-07-12 17:03:15
 **/
public class StaticInnerClassSingleton {

    /**
     * 构造方法私有化
     */
    private StaticInnerClassSingleton() {

    }

    /**
     * 静态内部类
     * 调用getInstance()方法时才进行加载, 实现懒加载的效果, 且是线程安全的
     */
    private static class SingletonHolder {
        private final static StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    /**
     * 加载内部静态类
     *
     * @return 返回单例对象
     */
    public static StaticInnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
