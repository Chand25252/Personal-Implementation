package com.chand.proxy.custom;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 自定义类装载器
 *
 * @author Chand
 * @Date: 2019-08-05 16:51:01
 */
public class MyClassLoader extends ClassLoader {

    private File classPathFile;

    public MyClassLoader() {
        String classPath = MyClassLoader.class.getResource("").getPath();
        classPathFile = new File(classPath);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        String className = MyClassLoader.class.getPackage().getName() + "." + name;
        if (classPathFile != null) {
            File file = new File(classPathFile, name + ".class");
            FileInputStream fileInputStream = null;
            ByteArrayOutputStream outputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                outputStream = new ByteArrayOutputStream();
                byte[] buff = new byte[1024];
                int len;
                while ((len = fileInputStream.read(buff)) != -1) {
                    outputStream.write(buff, 0, len);
                }
                return defineClass(className, outputStream.toByteArray(), 0, outputStream.size());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != fileInputStream) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (null != outputStream) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

}
