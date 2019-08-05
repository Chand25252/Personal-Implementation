package com.chand.proxy.jdk;

import com.chand.proxy.bean.Food;
import com.chand.proxy.bean.FoodImpl;
import com.sun.deploy.util.SessionState;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * JDK代理样例
 *
 * @author Chand
 * @Date: 2019-08-05 10:02:56
 */
public class JdkProxyExample {

    public static void main(String[] args) throws Exception {
        System.out.println("simpleProxy:");
        simpleProxy();
        System.out.println("stepProxy:");
        stepProxy();
    }

    /**
     * 通常使用的jdk代理方式
     */
    private static void simpleProxy() {
        // 生成jdk代理类
        Food foodProxy = (Food) Proxy.newProxyInstance(FoodImpl.class.getClassLoader(),
                FoodImpl.class.getInterfaces(), (proxy, method, params) -> {
                    System.out.println("----------before invoke----------");
                    method.invoke(new FoodImpl(), params);
                    System.out.println("----------after invoke----------");
                    return null;
                });
        // 代理类调用目标方法
        foodProxy.provideEnergy();
    }

    /**
     * 分步执行jdk代理过程
     *
     * @throws Exception
     */
    private static void stepProxy() throws Exception {
        // 0.设置在项目根目录生成代理类class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 1.获取代理类的类对象，主要设置相同的ClassLoader去加载目标类实现的接口Food
        Class<?> proxyClass = Proxy.getProxyClass(SessionState.Client.class.getClassLoader(), new Class[]{Food.class});

        // 2.得到代理类后，通过代理类的处理器句柄来得到构造器
        final Constructor<?> con = proxyClass.getConstructor(InvocationHandler.class);

        // 3.获取具体执行方法的句柄处理器，通过构造器传入被代理目标类对象，注入到代理类处理器句柄中进行代理调用
        final InvocationHandler handler = (proxy, method, params) -> {
            System.out.println("----------before invoke----------");
            method.invoke(new FoodImpl(), params);
            System.out.println("----------after invoke----------");
            return null;
        };

        // 4.通过构造器创建代理类
        Food foodProxy = (Food) con.newInstance(handler);

        // 5.调用目标方法
        foodProxy.provideEnergy();
    }
}
