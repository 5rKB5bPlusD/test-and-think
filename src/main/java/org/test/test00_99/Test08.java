package org.test.test00_99;

/**
 * 计算时针和分针的夹角度数
 */
public class Test08 {
    public static void main(String[] args) {
        int h = 9, m = 45;
        System.out.println(30 * h - 11 * m / 2.0);
        System.out.println(angleClock(h, m));
    }

    //计算时针和分针的夹角度数
    public static double angleClock(int hour, int minutes) {
        double angle = Math.abs((hour + minutes / 60.0) * 30 - minutes * 6);
        return Math.min(angle, 360 - angle);
    }
}
