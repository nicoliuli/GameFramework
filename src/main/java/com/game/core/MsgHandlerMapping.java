package com.game.core;

import com.game.core.dto.User;
import com.game.handler.AccountMsgHandler;

import java.util.HashMap;
import java.util.Map;

public class MsgHandlerMapping {

    private Map<String,MsgParamWrapper> map = new HashMap();

    public void reg(){
        AccountMsgHandler accountMsgHandler = new AccountMsgHandler();
        map.put("AccountMsgHandler.login",new MsgParamWrapper(accountMsgHandler::login,Object.class));
        map.put("AccountMsgHandler.verify",new MsgParamWrapper(accountMsgHandler::verify,User.class));
    }

    public MsgParamWrapper getMsgParamWrapper(String msgHandlerId){
        return map.get(msgHandlerId);
    }

}
