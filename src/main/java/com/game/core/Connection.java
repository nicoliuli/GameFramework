package com.game.core;

import io.netty.channel.Channel;

public class Connection {

    private int humanId;

    private final Channel channel;

    private final Port port;



    public Connection(int humanId, Channel channel, Port port) {
        this.humanId = humanId;
        this.channel = channel;
        this.port = port;
    }

    public int getHumanId() {
        return humanId;
    }

    public Channel getChannel() {
        return channel;
    }

    public Port getPort() {
        return port;
    }


}
