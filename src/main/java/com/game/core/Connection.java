package com.game.core;

import io.netty.channel.Channel;

public class Connection {

    private int humanId;

    private Channel channel;

    private Port port;

    public Connection() {
    }

    public Connection(int humanId, Channel channel, Port port) {
        this.humanId = humanId;
        this.channel = channel;
        this.port = port;
    }

    public int getHumanId() {
        return humanId;
    }
}
