package com.chand.map;

import static java.lang.Float.NaN;

/**
 * description
 *
 * @author Chand
 * @Date: 2019/7/22 13:47
 **/
public class App {


    static final int MAXIMUM_CAPACITY = 1 << 30;

    static int capacity = 2;

    public static void main(String[] args) {

        float f = NaN;

        System.out.println(f!=f);

//        int n = capacity - 1;
//        n |= n >>> 1;
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
//        System.out.println((n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1);
    }
}
