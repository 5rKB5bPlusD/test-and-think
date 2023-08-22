package org.test.test00_99;

/**
 * 问题：
 * 判断一个数是不是2的次方
 */
public class Test04 {
    public static void main(String[] args) {
        int a = 1024, b= 15, c=0;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(a-1));
        System.out.println(0b111111);

        System.out.println(Integer.valueOf(a&(a-1)).equals(0));
    }
}
