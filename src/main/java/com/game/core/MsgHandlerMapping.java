package com.game.core;

import com.game.core.constant.MsgHandlerConstant;
import com.game.core.dto.User;
import com.game.handler.AccountMsgHandler;

import java.util.HashMap;
import java.util.Map;

public class MsgHandlerMapping {

    /**
     * port 到 msgHandler的映射
     */
    private Map<String, MsgParamWrapper> msgHandlerMap = new HashMap();

    /**
     * service的回调队列
     */
    private Map<String, Object> callbackMap = new HashMap<>();

    public void reg() {
        AccountMsgHandler accountMsgHandler = new AccountMsgHandler();
        msgHandlerMap.put(MsgHandlerConstant.ACCOUNTMANAGER_VERIFY, new MsgParamWrapper(accountMsgHandler::login, Object.class));
        msgHandlerMap.put(MsgHandlerConstant.ACCOUNTMANAGER_EQUIP, new MsgParamWrapper(accountMsgHandler::verify, User.class));

    }

    public MsgParamWrapper getMsgParamWrapper(String msgHandlerId) {
        return msgHandlerMap.get(msgHandlerId);
    }

}
