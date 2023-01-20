package com.game.handler;

import com.game.core.Connection;
import com.game.core.HumanObject;
import com.game.core.MsgParam;
import com.game.core.Port;
import com.game.core.dto.User;
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

        User user = (User) msgParam.getParam();

        String result = "verify return portId = " + port.getPortId() +", humanId = " + humanId + ",param = " + user;
        channel.writeAndFlush(new TextWebSocketFrame(result));

    }
}
