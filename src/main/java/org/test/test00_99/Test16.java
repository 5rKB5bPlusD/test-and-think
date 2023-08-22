package org.test.test00_99;

import org.test.util.ArrayUtil;

import java.util.Arrays;

/**
 * @author 沁心
 * @version 1.0
 * @description 堆排序
 * @date 2023/8/2
 */
public class Test16 {
    public static void main(String[] args) {
        int[] array = ArrayUtil.getRandomArray(0, 200, 10);
        System.out.println(Arrays.toString(array));
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 堆排序
     *
     * @param array 数组
     * @see <a href="https://www.runoob.com/wp-content/uploads/2019/03/heapSort.gif">堆排序</a>
     */
    public static void heapSort(int[] array) {
        int n = array.length;

        // 建堆
        // 假设孩子节点的下标为i，父节点的下标为(i-1)/2，这里n是数组长度，那下标就是n-1，这是是从最后一个节点的父节点开始建堆，注意根节点为0
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // 排序
        // 从最后一个节点开始遍历，每次结束，排好一个最大值，将排好的从堆结构移除（也就是数组长度-1）
        for (int i = n - 1; i > 0; i--) {
            // 交换大顶堆的根节点和最后一个节点
            ArrayUtil.swap(array, i, 0);
            // 交换后，维护大顶堆结构
            heapify(array, i, 0);
        }
    }

    /**
     * 堆化，维护大顶堆结构
     *
     * @param array 数组
     * @param n     数组长度
     * @param i     父节点
     */
    private static void heapify(int[] array, int n, int i) {
        // 假设大顶堆的父节点下标是i，那么左孩子是2*i+1，右孩子是2*i+2，注意根节点从0开始
        int largest = i;
        int leftSon = 2 * i + 1;
        int rightSon = 2 * i + 2;
        // 比较三个节点的值，取出最大的下标
        if (leftSon < n && array[largest] < array[leftSon]) {
            largest = leftSon;
        }
        if (rightSon < n && array[largest] < array[rightSon]) {
            largest = rightSon;
        }
        // 如果最大值不是父节点，交换节点
        if (largest != i) {
            ArrayUtil.swap(array, largest, i);
            // 交换后原最大节点的下标已经不是最大的了，为了满足原位置大顶堆的结构，继续递归
            heapify(array, n, largest);
        }
    }
}
