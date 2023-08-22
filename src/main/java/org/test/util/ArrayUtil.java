package org.test.util;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * 数组工具
 */
public class ArrayUtil {
    /**
     * 返回一个随机int类型数组
     *
     * @param start 起始
     * @param end   结束（不包含）
     * @param size  长度
     * @return int[]
     */
    public static int[] getRandomArray(int start, int end, int size) {
        if (start >= end) {
            throw new RuntimeException("start必须小于end");
        }
        return IntStream.generate(() -> new Random().nextInt(end - start) + start).limit(size).toArray();
    }

    /**
     * 交换数组元素
     *
     * @param array 数组
     * @param i1  下标1
     * @param i2  下标2
     */
    public static void swap(String[] array, int i1, int i2) {
        if (i1 < array.length && i2 < array.length) {
            String temp = array[i1];
            array[i1] = array[i2];
            array[i2] = temp;
        }
    }

    /**
     * 交换数组元素
     *
     * @param array 数组
     * @param i1  下标1
     * @param i2  下标2
     */
    public static void swap(int[] array, int i1, int i2) {
        if (i1 < array.length && i2 < array.length) {
            int temp = array[i1];
            array[i1] = array[i2];
            array[i2] = temp;
        }
    }
}