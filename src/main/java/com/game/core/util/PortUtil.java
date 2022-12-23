package com.game.core.util;

import com.game.core.config.ServerConfig;

import java.util.concurrent.atomic.AtomicInteger;

public class PortUtil {
    private static AtomicInteger incr = new AtomicInteger(0);

    public static int portIndex() {
        return incr.getAndIncrement() % ServerConfig.PORT_COUNT;
    }

}
