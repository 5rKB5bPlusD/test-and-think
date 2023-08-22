package org.test.test00_99;

import org.test.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author 沁心
 * @version 1.0
 * @description 快速排序
 * @date 2023/8/2
 */
public class Test15 {
    public static void main(String[] args) {
        int[] array = ArrayUtil.getRandomArray(0, 200, 10);
        System.out.println(Arrays.toString(array));
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 快速排序
     *
     * @param array 数组
     * @param left  左下标
     * @param right 右下标
     * @see <a href="https://www.runoob.com/wp-content/uploads/2019/03/quickSort.gif">快速排序（基准值在左边）</a>
     */
    public static void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        // 分区，返回基准位置
        int index = part(array, left, right);
        // 递归左区间
        quickSort(array, left, index - 1);
        // 递归右区间
        quickSort(array, index + 1, right);
    }

    /**
     * 分区，返回基准位置
     *
     * @param array 数组
     * @param left  数组头
     * @param right 数组尾
     * @return 基准位置
     */
    private static int part(int[] array, int left, int right) {
        // 设置基准位置
        int pivot = right;
        int temp;
        // 将right指向left的位置，循环right
        for (right = left; right < pivot; right++) {
            // right小于pivot的时候，交换right和left，将小的移动到千面
            if (array[right] < array[pivot]) {
                temp = array[right];
                array[right] = array[left];
                // 并且将left+1
                array[left++] = temp;
            }
        }
        // left最终会指向第一个大于基准值的位置，交换后，左边全是小的，右边全是大或等于的
        temp = array[pivot];
        array[pivot] = array[left];
        array[left] = temp;
        return left;
    }
}
