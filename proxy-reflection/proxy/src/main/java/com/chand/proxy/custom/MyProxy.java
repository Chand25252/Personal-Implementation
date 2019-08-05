package com.chand.proxy.custom;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 自定义Proxy类
 *
 * @author Chand
 * @Date: 2019-08-05 16:51:17
 */
public class MyProxy {

    /**
     * 生成代理类实例
     *
     * @param myClassLoder
     * @param interfaces
     * @param h
     * @return
     */
    public static Object newProxyInstance(MyClassLoader myClassLoder, Class<?>[] interfaces, InvocationHandler h) {
        try {
            // 1.生成代理类java源码
            String src = generateSrc(interfaces);

            // 2.将源码输出到java文件中
            String filePath = MyProxy.class.getResource("").getPath();
            System.out.println(filePath);
            File f = new File(filePath + "$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();

            // 3.将java文件编译成class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manage = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = manage.getJavaFileObjects(f);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manage, null, null, null, iterable);
            task.call();
            manage.close();

            // 4.将class加载进jvm
            Class proxyClass = myClassLoder.findClass("$Proxy0");
            f.delete();

            // 5.返回代理类对象
            Constructor constructor = proxyClass.getConstructor(InvocationHandler.class);
            return constructor.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拼接生成的代理类$Proxy0的java文件内容(只支持单接口)
     *
     * @param interfaces
     * @return
     */
    private static String generateSrc(Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();
        sb.append("package com.chand.proxy.custom;" + "\r\n");
        sb.append("import java.lang.reflect.Method;" + "\r\n");
        sb.append("import java.lang.reflect.InvocationHandler;" + "\r\n");
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + "\r\n");
        sb.append("private InvocationHandler h;" + "\r\n");
        sb.append("public $Proxy0(InvocationHandler h) { " + "\r\n");
        sb.append("this.h = h;" + "\r\n");
        sb.append("}" + "\r\n");
        for (Method m : interfaces[0].getMethods()) {
            sb.append("public " + m.getReturnType().getName() + " "
                    + m.getName() + "() {" + "\r\n");
            sb.append("try{" + "\r\n");
            sb.append("Method m = " + interfaces[0].getName()
                    + ".class.getMethod(\"" + m.getName()
                    + "\",new Class[]{});" + "\r\n");
            sb.append("this.h.invoke(this,m,null);" + "\r\n");
            sb.append("}catch(Throwable e){" + "\r\n");
            sb.append("e.printStackTrace();" + "\r\n");
            sb.append("}" + "\r\n");
            sb.append("}" + "\r\n");
        }
        sb.append("}" + "\r\n");
//        System.out.println(sb.toString());
        return sb.toString();
    }
}
