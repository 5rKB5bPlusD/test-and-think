package org.test.test00_99;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 字符串split方法参数是自身
 */
public class Test09 {
    public static void main(String[] args) {
        String s = "123#ssss";
        String[] array = s.split(Pattern.quote("#"));
        System.out.println(array.length);
        System.out.println(Arrays.toString(array));
    }
}
