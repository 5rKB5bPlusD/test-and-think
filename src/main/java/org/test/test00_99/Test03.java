package org.test.test00_99;

/**
 * 问题：
 * 计算指定范围内有多少个素数
 */
public class Test03 {
    public static void main(String[] args) {
        method(101, 200);
    }

    public static void method(int start, int end) {
        int count = 0;
        boolean flag = false;
        for (int i = start; i <= end; i++) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
            }
            if (flag) {
                count++;
                System.out.println(i);
            }
        }

        System.out.println(start + "到" + end + "之间有" + count + "个素数");

    }
}
