package com.chand.map;

/**
 * HashMap实现类
 *
 * @author Chand
 * @Date: 2019-07-19 16:34:47
 **/
public class MyHashMap<K, V> {

    /**
     * 数组链表结构, 用于存储键值对数据
     */
    private Entry<K, V>[] table;

    /**
     * 默认容量16
     */
    private static final int DEFAULT_CAPACITY = 1 << 4;

    /**
     * 最大容量2的30次方
     */
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 负载因子, 默认0.75f
     */
    private float loadFactor = 0.75f;

    /**
     * resize阈值, 通常为2的N次幂
     */
    private int threshold;

    /**
     * map当前大小
     */
    private int size;

    /**
     * 默认构造方法
     */
    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 指定容量的构造方法
     */
    public MyHashMap(int capacity) {
    }

    /**
     * 指定容量与负载因子的构造方法
     */
    public MyHashMap(int capacity, float loadFactor) {
        this.loadFactor = loadFactor;
        this.threshold = this.getSize(capacity);
    }

    private static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private static int getSize(int capacity) {
        int n = capacity - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    private static int getIndex(int h, int length) {
        return h & (length-1);
    }

    public void put(K key, V value) {

    }

    public V get(K key) {
        return null;
    }

    public V remove(K key) {
        return null;
    }

    public boolean containsKey() {
        return false;
    }

    class Entry<K, V> {
        K key;
        V value;
        int hash;
        Entry<K, V> next;
        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

}
