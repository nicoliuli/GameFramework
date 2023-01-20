package com.game.handler;

import com.game.core.HumanObject;
import com.game.core.MsgParam;
import com.game.core.dto.User;
import com.game.manager.AccountManager;

public class AccountMsgHandler {

    public void login(MsgParam msgParam){
        System.out.println(msgParam.getParam());
    }

    public void verify(MsgParam msgParam){
        HumanObject humanObject = msgParam.getHumanObject();


        User user = (User) msgParam.getParam();

      //  String result = "verify return portId = " + port.getPortId() +", humanId = " + humanId + ",param = " + user;
      //  channel.writeAndFlush(new TextWebSocketFrame(result));

        new AccountManager().verify(humanObject,user);

    }
}
