package com.game.core.obj;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class HumanObject {
    private Integer humanId;
    private Connection connection;

    // 还可以继续封装其他数据

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
