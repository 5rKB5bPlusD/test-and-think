package org.test.test00_99;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 问题：
 * 编写一个截取字符串的函数，输入为一个字符串和字节数，输出为按字节截取的字符串。但是要保证汉字不被截半个，例如“人abc”，
 * 应该截为“人ab”;输入“人abc们”，应该截为“人abc”,而不是截为“人ab+们的半个”
 */
public class Test01 {

    public static void main(String[] args) {
        String str = "abc汉字";
        int len = 7;

        byte[] bytes = str.getBytes();
        char[] chars = str.toCharArray();
        System.out.println(Arrays.toString(bytes));
        System.out.println(Arrays.toString(chars));

        //不会截取到半个汉字
        System.out.println("不会截取到半个汉字: " + str.substring(0, len > str.length() ? str.length() : len));
        //会截取到半个汉字
        System.out.println("会截取到半个汉字: " + new String(bytes, 0, len));

        System.out.println(subStringByBytes(str, len));
    }

    public static String subStringByBytes(String str, int len) {
        if (str == null) {
            return null;
        }
        int encodingNum = 2;
        //判断编码是不是UTF-8，UTF-8占3个字节
        if (str.equals(new String(str.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8))) {
            encodingNum = 3;
        }
        System.out.println("encodingNum:" + encodingNum);

        char[] chars = str.toCharArray();
        int i = 0, end = 0;
        while (end < len && i < chars.length) {
//            汉字占用字符大于1
            if (String.valueOf(chars[i]).getBytes().length > 1) {
//                打开注释去掉最后一个残缺汉字
//                if (end + encodingNum > len) {
//                    break;
//                }
                end += encodingNum;
            } else {
                end++;
            }
            i++;
        }
        return new String(str.getBytes(), 0, end);
    }
}
