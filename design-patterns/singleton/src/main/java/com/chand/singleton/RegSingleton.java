package com.chand.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例注册表(登记式单例)
 *
 * @author Chand
 * @Date: 2019-07-12 22:01:09
 **/
public class RegSingleton {

    /**
     * 简单实现Spring的bean管理
     */
    private static Map<String, Object> beanFactory = new HashMap<>();

    /**
     * 构造方法私有化
     */
    private RegSingleton() {

    }

    /**
     * 获取bean
     *
     * @param className
     * @return 返回单例对象
     */
    public static Object getBean(String className) {
        Object bean = null;
        if (beanFactory.containsKey(className)) {
            bean = beanFactory.get(className);
        } else {
            try {
                bean = Class.forName(className).newInstance();
                beanFactory.put(className, bean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bean;
    }

}
