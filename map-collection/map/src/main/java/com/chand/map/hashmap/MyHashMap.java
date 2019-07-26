package com.chand.map.hashmap;

/**
 * HashMap实现类
 *
 * @author Chand
 * @Date: 2019-07-19 16:34:47
 */
public class MyHashMap<K, V> {

    /**
     * 桶, 数组链表结构, 用于存储键值对数据
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
     * @return 阈值
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

    /**
     * 返回当前键值对个数
     *
     * @return size
     */
    public int size() {
        return this.size;
    }

    /**
     * 放入键值对
     *
     * @param key
     * @param value
     * @return 返回被替换的value值
     */
    public V put(K key, V value) {
        // 如果当前桶为空
        if (table == null || table.length == 0) {
            table = resize();
        }
        // 获取key的hash值
        int hash = hash(key.hashCode());
        // 获取key在桶的位置
        int i = getIndex(hash, table.length);
        // 遍历该位置链表
        for (Entry<K, V> e = table[i]; e != null; e = e.next) {
            Object k = e.key;
            // 如果key在链表中已存在，则替换为新value
            if (e.hash == hash && (k == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
            // 如果key不存在, 则添加到链表末尾
            if (e.next == null) {
                e.next = new Entry<>(hash, key, value, null);
                if (size++ >= threshold) {
                    resize();
                }
                return null;
            }
        }
        // 如果该位置为空
        table[i] = new Entry<>(hash, key, value, null);
        // 判断是否需要扩容
        if (size++ >= threshold) {
            resize();
        }
        return null;
    }

    /**
     * 借鉴(抄袭)HashMap的resize方法(去掉红黑树部分)
     *
     * @return 扩容之后的桶table
     */
    private Entry<K, V>[] resize() {
        Entry<K, V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_CAPACITY) {
                newThr = oldThr << 1;
            }
        } else if (oldThr > 0) {
            newCap = oldThr;
        } else {
            newCap = DEFAULT_CAPACITY;
            newThr = (int) (loadFactor * DEFAULT_CAPACITY);
        }
        if (newThr == 0) {
            float ft = (float) newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ? (int) ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"rawtypes", "unchecked"})
        Entry<K, V>[] newTab = (Entry<K, V>[]) new Entry[newCap];
        table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                Entry<K, V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null) {
                        newTab[e.hash & (newCap - 1)] = e;
                    } /*else if (e instanceof TreeNode) {
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    }*/ else {
                        Entry<K, V> loHead = null, loTail = null;
                        Entry<K, V> hiHead = null, hiTail = null;
                        Entry<K, V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            } else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    /**
     * 根据key值获取value
     *
     * @param key
     * @return value值
     */
    public V get(Object key) {
        // key为空, 直接返回null
        if (key == null || size == 0) {
            return null;
        }
        // 获取hash值
        int hash = hash(key.hashCode());
        // 获取位置
        int index = getIndex(hash, table.length);
        // 遍历该位置链表
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                return e.getValue();
            }
        }
        return null;
    }

    /**
     * 根据key值删除键值对
     *
     * @param key
     * @return value值
     */
    public V remove(Object key) {
        // key为空, 直接返回null
        if (key == null || size == 0) {
            return null;
        }
        // 获取hash值
        int hash = hash(key.hashCode());
        // 获取位置
        int index = getIndex(hash, table.length);
        // 遍历该位置链表
        Entry<K, V> temp = null;
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                if (temp == null) {
                    table[index] = null;
                } else {
                    temp.next = e.next;
                }
                return e.getValue();
            }
            // 记录前一个节点
            temp = e;
        }
        return null;
    }

    /**
     * 判断key值是否存在
     *
     * @param key
     * @return true or false
     */
    public boolean containsKey(Object key) {
        int hash = hash(key.hashCode());
        int index = getIndex(hash, table.length);
        for (Entry<K,V> e = table[index]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                return true;
            }
        }
        return false;
    }

    static class Entry<K, V> {
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
