package com.game.core.util;

public class Log {

    public static void info(String info) {
        System.out.print(info);
    }
    public static void info(String info, Object param) {
        System.out.println(info + param);
    }

    public static void info(String info, Object param1, Object param2) {
        System.out.println(info + param1 + param2);
    }

    public static void info(String info, Object param1, Object param2, Object param3) {
        System.out.println(info + param1 + param2 + param3);
    }
    public static void info(String info, Object param1, Object param2, Object param3,Object param4) {
        System.out.println(info + param1 + param2 + param3 + param4);
    }
}
