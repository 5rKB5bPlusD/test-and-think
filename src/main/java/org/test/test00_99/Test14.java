package org.test.test00_99;

import org.test.util.ArrayUtil;

import java.util.Arrays;

/**
 * 递归实现全排列
 */
public class Test14 {
    public static void main(String[] args) {
        String[] array = new String[]{"a", "b", "c"};
        permutation(array, 0, array.length);
    }

    /**
     * 全排列
     *
     * @param array 数组
     * @param m   头元素下标
     * @param n   数组长度
     */
    public static void permutation(String[] array, int m, int n) {
        // 超级操作基础情况，在这个情况可以直接得出结果，终止递归
        // 当m==n说明后面没有元素了，这一次排列完成
        if (m == n) {
            System.out.println(Arrays.toString(array));
            return;
        }

        for (int i = m; i < n; i++) {
            // 微操作，确定头元素
            ArrayUtil.swap(array, m, i);
            // 超级操作，递归除头元素外全排列
            permutation(array, m + 1, n);
            // 微操作，复原数组,防止重复排列
            ArrayUtil.swap(array, m, i);
        }
    }

}
