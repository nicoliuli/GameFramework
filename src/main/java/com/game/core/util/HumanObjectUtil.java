package com.game.core.util;

import java.util.concurrent.atomic.AtomicInteger;

public class HumanObjectUtil {

    private static AtomicInteger incr = new AtomicInteger(1);

    public static Integer genHumanId() {
        return incr.getAndIncrement();
    }
}
