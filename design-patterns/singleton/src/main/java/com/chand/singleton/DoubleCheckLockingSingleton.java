package com.chand.singleton;

/**
 * 双重校验锁单例模式
 *
 * @author Chand
 * @Date: 2019-07-12 14:56:41
 */
public class DoubleCheckLockingSingleton {

    /**
     * 静态私有成员, 不进行初始化
     * 加volatile关键字, 禁止JVM指令重排, 保证线程间的可见性
     */
    private static volatile DoubleCheckLockingSingleton instance = null;

    /**
     * 构造方法私有化
     */
    private DoubleCheckLockingSingleton() {

    }

    /**
     * 只有第一次访问时, 才创建实例, 实现懒加载
     *
     * @return 返回单例对象
     */
    public static DoubleCheckLockingSingleton getInstance() {
        // 第一重校验
        if (instance == null) {
            // 加同步锁, 防止多线程并发创建多个实例的问题
            synchronized (DoubleCheckLockingSingleton.class) {
                // 第二重校验
                if (instance == null) {
                    instance = new DoubleCheckLockingSingleton();
                }
            }
        }
        return instance;
    }
}
