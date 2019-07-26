package com.chand.hashmap;

/**
 * description
 *
 * @author Chand
 * @Date: 2019/7/22 13:47
 */
public class App {

    public static void main(String[] args) {
        MyHashMap<String, String> mapTest = new MyHashMap<>();
        for (int i = 0; i < 20; i++) {
            mapTest.put(i + "", "v" + i);
        }
        mapTest.remove("10");
        for (int i = 0; i < 20; i++) {
            System.out.println(mapTest.get(i + ""));
        }
    }
}
