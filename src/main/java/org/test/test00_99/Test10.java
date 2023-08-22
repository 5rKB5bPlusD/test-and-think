package org.test.test00_99;

import java.util.Arrays;

/**
 * 归并排序
 */
public class Test10 {
    public static void main(String[] args) {
        int[] array = new int[]{2, 10, 5, 2, 1, 6, 7, 3, 1, 23, 1, -1, -5};
        mergeSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    // 归并排序
//    public static void merge(int[] nums1, int m, int[] nums2, int n) {
//        System.out.println("before: " + Arrays.toString(nums1));
//        System.out.println();
//        int i = m - 1;
//        int j = n - 1;
//        int k = m + n - 1;
//        while (i >= 0 && j >= 0) {
//            String[] arr1 = Arrays.stream(nums1).mapToObj(String::valueOf).toArray(String[]::new);
//            arr1[k] = arr1[k] == null ? "k" : arr1[k] + " k";
//            arr1[i] = arr1[i] == null ? "i" : arr1[i] + " i";
//            System.out.println(Arrays.toString(arr1));
//            String[] arr2 = Arrays.stream(nums2).mapToObj(String::valueOf).toArray(String[]::new);
//            arr2[j] = arr2[j] == null ? "j" : arr2[j] + " j";
//            System.out.println(Arrays.toString(arr2));
//
//            nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
//
//            System.out.println("after: " + Arrays.toString(nums1));
//            System.out.println();
//        }
//        while (j >= 0) {
//            nums1[k--] = nums2[j--];
//        }
//        System.out.println(Arrays.toString(nums1));
//    }

    /**
     * 递归实现归并排序
     * @param nums 数组
     * @param start 起点下标
     * @param end 终点下标
     * @see <a href="https://www.runoob.com/wp-content/uploads/2019/03/mergeSort.gif">归并排序</a>
     */
    public static void mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        // 微操作，计算中间点位置
        int mid = (start + end) / 2;
        // 超级操作，递归划分左边
        mergeSort(nums, start, mid);
        // 超级操作，递归划分右边
        mergeSort(nums, mid + 1, end);
        // 微操作，归并
        merge(nums, start, mid, end);
    }

    public static void merge(int[] nums, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        // 遍历两个数组，比较并按序归并到新数组
        while (i <= mid && j <= end) {
            temp[k++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }
        // 如果左边数组未结束，全部复制到新数组
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        // 如果右边数组未结束，全部复制到新数组
        while (j <= end) {
            temp[k++] = nums[j++];
        }
        System.arraycopy(temp, 0, nums, start, temp.length);
    }
}
