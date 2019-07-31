package com.chand.list.ArrayList;

/**
 * @author Chand
 * @Date: 2019-07-29 15:16:15
 */
public class App {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add((int) (Math.random() * 100) + String.valueOf(i));
        }
        System.out.println("----------------------");
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("----------------------");
        System.out.println(list.isEmpty());
        list.remove(5);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("----------------------");
        System.out.println(list.size());
        for (int i = 0; i < 10; i++) {
            list.remove(0);
        }
        System.out.println(list.isEmpty());
        System.out.println(list.size());
        System.out.println("----------------------");
    }
}
