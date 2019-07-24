package com.chand.map;

/**
 * HashMap实现类
 *
 * @author Chand
 * @Date: 2019-07-19 16:34:47
 */
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
     * resize阈值, 通常为2的指数幂
     */
    private int threshold;

    /**
     * map当前大小(当前map中的键值对个数)
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
     *
     * @param capacity
     */
    public MyHashMap(int capacity) {
    }

    /**
     * 指定容量与负载因子的构造方法
     *
     * @param capacity
     * @param loadFactor
     */
    public MyHashMap(int capacity, float loadFactor) {
        this.loadFactor = loadFactor;
        this.threshold = this.getThreshold(capacity);
    }

    /**
     * 获取触发resize的阈值
     *
     * @param capacity
     * @return
     */
    private static int getThreshold(int capacity) {
        // 位运算, 取距离capacity最近的2的指数幂
        int n = capacity - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /**
     * 对key进行hash
     *
     * @param key
     * @return hash值
     */
    private final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 对h进行取模运算, 获取元素所在位置
     *
     * @param h
     * @param length
     * @return 元素所在位置
     */
    private int getIndex(int h, int length) {
        return h & (length - 1);
    }

    private int resize() {
        return 16;
    }

    /**
     * 放入键值对
     *
     * @param key
     * @param value
     */
    public V put(K key, V value) {
        if (table == null || table.length == 0) {

        }
        int hash = hash(key.hashCode());
        int i = getIndex(hash, table.length);
        //遍历链表
        for (Entry<K, V> e = table[i]; e != null; e = e.next) {
            Object k;
            //如果key在链表中已存在，则替换为新value
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
//        addEntry(hash, key, value, i);
        return null;
    }

    /**
     * 根据key值获取value
     *
     * @param key
     * @return value值
     */
    public V get(K key) {
        return null;
    }

    /**
     * 根据key值删除键值对
     *
     * @param key
     * @return value值
     */
    public V remove(K key) {
        return null;
    }

    /**
     * 判断key值是否存在
     *
     * @param key
     * @return true or false
     */
    public boolean containsKey(K key) {
        return false;
    }

    class Entry<K, V> {
        K key;
        V value;
        int hash;
        Entry<K, V> next;

        public Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            this.value = value;
            return this.value;
        }
    }

}
