package com.chand.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Float.NaN;

/**
 * description
 *
 * @author Chand
 * @Date: 2019/7/22 13:47
 */
public class App {


    static final int MAXIMUM_CAPACITY = 1 << 30;

    static int capacity = 2;

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        List<Character> mappers = list.stream().map(num -> {
            char ch = (char) ('a' + num);
            return ch;
        }).collect(Collectors.toList());

        int sum = list.stream().reduce((x, y) -> x + y).get();

        mappers.forEach(num -> System.out.println(num));

        System.out.println(sum);


//        int n = capacity - 1;
//        n |= n >>> 1;
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
//        System.out.println((n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1);
    }
}
