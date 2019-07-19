package com.chand.map;

/**
 * HashMap实现类
 *
 * @author Chand
 * @Date: 2019-07-19 16:34:47
 **/
public class MyHashMap<K, V> {

    public MyHashMap() {
        this(DEFAULT_SIZE);
    }

    public MyHashMap(int size) {
        table = (Entry<K, V>[])new Entry[size];
    }

    public MyHashMap(int size, float loadFactor) {
        this.loadFactor = loadFactor;
        table = (Entry<K, V>[])new Entry[size];
    }

    void put(K key, V value) {

    }

    V get(K key) {
        return null;
    }

    boolean containsKey() {
        return false;
    }

    class Entry<K, V> {
        K key;
        V value;
    }

    Entry<K, V>[] table;

    static final int DEFAULT_SIZE = 16;

    float loadFactor = 0.75f;

}
