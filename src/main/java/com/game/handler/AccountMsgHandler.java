package com.game.handler;

import com.game.core.Connection;
import com.game.core.HumanObject;
import com.game.core.MsgParam;
import com.game.core.Port;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class AccountMsgHandler {

    public void login(MsgParam msgParam){
        System.out.println(msgParam.getParam());
    }

    public void verify(MsgParam msgParam){
        HumanObject humanObject = msgParam.getHumanObject();
        Connection connection = humanObject.getConnection();
        Integer humanId = humanObject.getHumanId();
        Port port = connection.getPort();
        Channel channel = connection.getChannel();

        String result = "verify return portId = " + port.getPortId() +", humanId = " + humanId;
        channel.writeAndFlush(new TextWebSocketFrame(result));

    }
}
