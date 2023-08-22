package org.test.test00_99;

import java.util.function.Supplier;

/**
 * 线程回调方法
 */
public class Test11 {
    public static void main(String[] args) {
        new Thread(() -> {
            long time = System.currentTimeMillis();
            Test11.callback(() -> String.valueOf(time));
        }).start();
    }

    public static void callback(Supplier<String> supplier) {
        String str = supplier.get();
        System.out.println("callback: " + str);
    }
}