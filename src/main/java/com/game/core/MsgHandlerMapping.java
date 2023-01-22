package com.game.core;

import com.game.core.constant.MsgHandlerConstant;
import com.game.core.dto.User;
import com.game.handler.AccountMsgHandler;
import com.game.manager.AccountManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 单实例
 */
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
        //======= 注册MsgHandler
        AccountMsgHandler accountMsgHandler = AccountMsgHandler.inst();
        msgHandlerMap.put(MsgHandlerConstant.ACCOUNTMSGHANDLER_LOGIN, new MsgParamWrapper(accountMsgHandler::login, Object.class));
        msgHandlerMap.put(MsgHandlerConstant.ACCOUNTMSGHANDLER_VERIFY, new MsgParamWrapper(accountMsgHandler::verify, User.class));


        //======== 注册callback
        AccountManager accountManager = AccountManager.inst();


    }

    public MsgParamWrapper getMsgParamWrapper(String msgHandlerId) {
        return msgHandlerMap.get(msgHandlerId);
    }

}
