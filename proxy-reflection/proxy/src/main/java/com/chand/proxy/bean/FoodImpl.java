package com.chand.proxy.bean;

/**
 * 被代理类
 *
 * @author Chand
 * @Date: 2019-08-05 09:40:07
 */
public class FoodImpl implements Food {
    @Override
    public void provideEnergy() {
        System.out.println("Food can provide energy!");
    }
}
