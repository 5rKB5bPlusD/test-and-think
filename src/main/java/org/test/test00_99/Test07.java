package org.test.test00_99;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * volatile数组内存刷新感知
 * 当一个线程在读取一个使用volatile修饰的变量的时候，会将该线程中所有使用的变量从主内存中重新读取
 */
public class Test07 {
    static volatile Integer[] arr1 = new Integer[5];
    static Integer[] arr2 = new Integer[5];

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME));
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arr1[0] = 1;
        }).start();

        while (arr1[0] == null) {

        }
        //内部元素修改能让更改被感知到
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME));

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arr1 = new Integer[1];
        }).start();

        while (arr1.length == 5) {

        }
        //外部引用更改能让更改被感知到
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME));

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arr2[0] = 1;
        }).start();

        while (arr2[0] == null) {
            //另一个无关volatile变量的读取可以让arr2的更改被感知到
            arr1[0] = 0;
            //随便加个锁可以让arr2的更改被感知到
            synchronized (arr2){}
        }
        //外部引用更改能检测到
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_TIME));
    }
}
