package com.chand.list.ArrayList;

import java.util.Arrays;

/**
 * ArrayList实现类
 *
 * @author Chand
 * @Date: 2019-07-29 14:42:54
 */
public class MyArrayList<E> {

    /**
     * list中的元素个数
     */
    private int size = 0;

    /**
     * 默认容量为10
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 通过数组保存数据
     */
    private Object[] elementData;

    /**
     * 无参数构造方法
     */
    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 指定list长度的构造方法
     *
     * @param size
     */
    public MyArrayList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        this.elementData = new Object[size];
    }

    /**
     * 向list中添加数据
     *
     * @param e
     */
    public void add(E e) {
        if (elementData.length == size) {
            int newSize = size + (size >> 1);
            resize(newSize);
        }
        elementData[size++] = e;
    }

    /**
     * 根据索引从list中获取数据
     *
     * @param index
     * @return
     */
    public E get(int index) {
        return (E) elementData[index];
    }

    /**
     * 移除索引位置的数据
     *
     * @param index
     */
    public void remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        }
        if (index >= size) {
            return;
        }
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
    }

    /**
     * 判断list是否为空
     *
     * @return true or false
     */
    public boolean isEmpty() {
        boolean f = false;
        if (size == 0) {
            f = true;
        }
        return f;
    }

    /**
     * 获取list长度
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * list扩容
     *
     * @param newSize
     */
    private void resize(int newSize) {
        elementData = Arrays.copyOf(elementData, newSize);
    }


}
