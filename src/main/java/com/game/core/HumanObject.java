package com.game.core;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class HumanObject {
    private Integer humanId;
    private Connection connection;

    public Integer getHumanId() {
        return humanId;
    }

    public void setHumanId(Integer humanId) {
        this.humanId = humanId;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void sendMsg(String msg){
        this.connection.getChannel().writeAndFlush(new TextWebSocketFrame(msg));
    }
}
