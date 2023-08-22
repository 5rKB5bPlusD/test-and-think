package org.test.test00_99;

import org.test.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 简单排序算法
 */
public class Test13 {
    public static void main(String[] args) {
        int[] array = ArrayUtil.getRandomArray(10, 100, 10);
        System.out.println(Arrays.toString(array));
        bubbleSort(array);
        System.out.println(Arrays.toString(array) + "\n");

        array = ArrayUtil.getRandomArray(10, 100, 10);
        System.out.println(Arrays.toString(array));
        selectionSort(array);
        System.out.println(Arrays.toString(array) + "\n");

        array = ArrayUtil.getRandomArray(10, 100, 10);
        System.out.println(Arrays.toString(array));
        insertionSort(array);
        System.out.println(Arrays.toString(array) + "\n");

        array = ArrayUtil.getRandomArray(10, 100, 10);
        System.out.println(Arrays.toString(array));
        shellSort(array);
        System.out.println(Arrays.toString(array) + "\n");

        array = ArrayUtil.getRandomArray(0, 100, 20);
        System.out.println(Arrays.toString(array));
        countingSort(array);
        System.out.println(Arrays.toString(array) + "\n");

        array = ArrayUtil.getRandomArray(-10, 100, 10);
        System.out.println(Arrays.toString(array));
        radixSort(array);
        System.out.println(Arrays.toString(array) + "\n");

        array = ArrayUtil.getRandomArray(-10, 100, 10);
        System.out.println(Arrays.toString(array));
        bucketSort(array);
        System.out.println(Arrays.toString(array) + "\n");
    }


    /**
     * 冒泡排序
     *
     * @param array 待排数组
     * @see <a href="https://www.runoob.com/wp-content/uploads/2019/03/bubbleSort.gif">冒泡排序</a>
     */
    public static void bubbleSort(int[] array) {
        int temp;
        for (int i = 1; i < array.length; i++) {
            // 记录是否进行了交换
            boolean isExchanged = false;
            // 内层每次循环尾部减少外层循环的次数，每次循环结束高的都移动到了尾部
            for (int j = 0; j < array.length - i; j++) {
                // 比较前一个和后一个，前大于后则交换
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isExchanged = true;
                }
            }
            // 如果没有进行交换说明排序结束
            if (!isExchanged) {
                break;
            }
        }
    }

    /**
     * 选择排序
     *
     * @param array 待排数组
     * @see <a href="https://www.runoob.com/wp-content/uploads/2019/03/selectionSort.gif">选择排序</a>
     */
    public static void selectionSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            // 记录每次循环最小的值的下标
            int min = i;
            // 内层每次循环开头都增加外层循环的次数，每次循环结束都将最小值移动到前面记录的下标位置
            for (int j = i + 1; j < array.length; j++) {
                //比较修改最小值下标
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            // 判断最小值的下标和代移动下标，有变化交换位置
            if (min != i) {
                temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
    }

    /**
     * 插入排序
     *
     * @param array 待排数组
     * @see <a href="https://www.runoob.com/wp-content/uploads/2019/03/insertionSort.gif">插入排序</a>
     */
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            // 取出待插入元素
            int temp = array[i];
            int j = i;
            // 比较待插入元素和前一位的大小，比前一位小，数组前一位往后移动
            while (j > 0 && temp < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            // 就j!=i说明数组移动过，将元素插入到当前位置（比待插入元素大的都移动到后面去了）
            if (j != i) {
                array[j] = temp;
            }
        }
    }

    /**
     * 希尔排序
     *
     * @param array 待排数组
     */
    public static void shellSort(int[] array) {
        int length = array.length;
        // 步长，初始为长度的1/2，每次循环变为上次的1/2
        for (int step = length / 2; step >= 1; step /= 2) {
            // 这里开始就是插入排序，只是从原来的步长1变成step
            for (int i = step; i < length; i += step) {
                int temp = array[i];
                int j = i;
                while (j > 0 && temp < array[j - step]) {
                    array[j] = array[j - step];
                    j -= step;
                }
                if (j != i) {
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 计数排序（非比较排序）
     * 一般计数排序的要求是元素为正整数的数组（或者是可以适用计数想法的数组）
     *
     * @param array 数组
     * @see <a href="https://www.runoob.com/wp-content/uploads/2019/03/countingSort.gif">计数排序</a>
     */
    public static void countingSort(int[] array) {
        if (IntStream.of(array).anyMatch(value -> value < 0)) {
            throw new RuntimeException("排序数组不满足要求");
        }

        // 取出最大值，最大值+1是计数数组的长度
        int max = 0;
        for (int i : array) {
            max = Math.max(max, i);
        }

        // 构造计数数组，数组从0开始所以+1
        int[] countArr = new int[max + 1];
        for (int i : array) {
            countArr[i]++;
        }

        // 计数数组转换为累计数组，累计数组是为了稳定性
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1];
        }

        int[] tempArr = new int[array.length];
        // 倒序遍历原数组，从累计数组中取值，累计数组的值-1是当前遍历元素出现的最后一个位置
        for (int i = array.length - 1; i >= 0; i--) {
            tempArr[--countArr[array[i]]] = array[i];
        }

        System.arraycopy(tempArr, 0, array, 0, array.length);
    }

    /**
     * 基数排序
     * 一般基数排序的要求是元素为整数的数组（或者是可以适用基数想法的数组）
     *
     * @param array 数组
     * @see <a href="https://www.runoob.com/wp-content/uploads/2019/03/radixSort.gif">基数排序</a>
     */
    public static void radixSort(int[] array) {
        // 计算最大值
        int max = 0;
        for (int i : array) {
            max = Math.max(max, i);
        }

        // 计算最大值位数
        int maxNumLength = 0;
        do {
            maxNumLength++;
        } while ((max /= 10) > 0);

        // mod是每次取模的数值，dev是除数
        for (int i = 0, mod = 10, dev = 1; i < maxNumLength; i++, mod *= 10, dev *= 10) {
            // 存放0-9个数。增加一倍，存储负数
            ArrayList<Integer>[] listArray = new ArrayList[20];
            for (int j = 0; j < listArray.length; j++) {
                listArray[j] = new ArrayList<>();
            }

            for (int value : array) {
                // 计算每次位置的数字，+10之后正数分布在10-19，负数分布在0-9
                int index = (value % mod) / dev + 10;
                listArray[index].add(value);
            }

            //遍历listArray，按序覆盖原数组
            int k = 0;
            for (ArrayList<Integer> integers : listArray) {
                for (Integer integer : integers) {
                    array[k++] = integer;
                }
            }
        }
    }

    /**
     * 桶排序
     * 思想就是分治，先划分元素到桶，再对每个桶的元素排序，最后合并
     *
     * @param array 数组
     */
    public static void bucketSort(int[] array) {
        // 1. 先划分桶，什么方法都行
        // 计算最大值最小值
        int max = 0, min = 0;
        for (int value : array) {
            max = Math.max(max, value);
            min = Math.min(min, value);
        }

        // 设置步长（每个桶放的元素范围），然后把元素放到桶里面
        int step = 10, count = (max - min) / step + 1;
        ArrayList<Integer>[] listArray = new ArrayList[count];
        for (int i = 0; i < listArray.length; i++) {
            listArray[i] = new ArrayList<>();
        }
        for (int value : array) {
            int index = (value - min) / step;
            listArray[index].add(value);
        }

        // 2. 再对桶内的元素排序
        // 对桶内的元素排序，这里用的自带的排序
        for (int i = 0; i < listArray.length; i++) {
            int[] tempArray = listArray[i].parallelStream().mapToInt(value -> value).toArray();
            Arrays.sort(tempArray);
            listArray[i] = IntStream.of(tempArray).boxed().collect(Collectors.toCollection(ArrayList::new));
        }

        // 合并各个桶内的元素
        int k = 0;
        for (ArrayList<Integer> integers : listArray) {
            for (Integer integer : integers) {
                array[k++] = integer;
            }
        }
    }
}
