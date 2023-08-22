package org.test.test00_99;

import java.util.*;

/**
 * 问题：
 * 判断数组中有没有重复元素
 */
public class Test02 {

    public static void main(String[] args) {
        Character[] array = {'1', '3', '3'};
        Set<Character> set = new TreeSet<>(Arrays.asList(array));
        System.out.println(array.length == set.size());
    }
}
