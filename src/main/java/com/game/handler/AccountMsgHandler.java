package com.game.handler;

import com.game.core.HumanObject;
import com.game.core.MsgParam;
import com.game.core.anno.MsgController;
import com.game.core.dto.User;
import com.game.manager.AccountManager;

public class AccountMsgHandler extends MsgHandler {

    public static AccountMsgHandler inst() {
        return inst(AccountMsgHandler.class);
    }

    @MsgController
    public void login(MsgParam msgParam) {
        System.out.println(msgParam.getParam());
    }

    @MsgController
    public void verify(MsgParam msgParam) {
        HumanObject humanObject = msgParam.getHumanObject();
        User user = (User) msgParam.getParam();
        AccountManager.inst().verify(humanObject, user);
    }
}
