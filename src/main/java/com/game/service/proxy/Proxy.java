package com.game.service.proxy;

import com.game.core.Port;

abstract public class Proxy {
    // 从port里取service
    final Port port;

    public Proxy(Port port) {
        this.port = port;
    }
}
