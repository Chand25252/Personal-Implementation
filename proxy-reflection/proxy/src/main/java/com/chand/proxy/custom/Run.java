package com.chand.proxy.custom;

import com.chand.proxy.bean.Food;
import com.chand.proxy.bean.FoodImpl;

/**
 * 测试
 *
 * @author Chand
 * @Date: 2019-08-05 17:26:46
 */
public class Run {
    public static void main(String[] args) {
        // 生成代理对象
        Food foodProxy = (Food) MyProxy.newProxyInstance(new MyClassLoader(),
                FoodImpl.class.getInterfaces(), (proxy, method, params) -> {
                    System.out.println("----------before invoke----------");
                    method.invoke(new FoodImpl(), params);
                    System.out.println("----------after invoke----------");
                    return null;
                });
        // 执行目标方法
        foodProxy.provideEnergy();
    }
}
