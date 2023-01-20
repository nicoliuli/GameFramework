package com.game.core;

import com.game.core.func.Func1;
import com.game.handler.AccountMsgHandler;

import java.util.HashMap;
import java.util.Map;

public class MsgHandlerMapping {

    private Map<String,Func1<MsgParam>> map = new HashMap();

    public void reg(){
        AccountMsgHandler accountMsgHandler = new AccountMsgHandler();
        map.put("AccountMsgHandler.login",accountMsgHandler::login);
        map.put("AccountMsgHandler.verify",accountMsgHandler::verify);
    }

    public Func1 getMsgHander(String msgHandlerId){
        return map.get(msgHandlerId);
    }

}
