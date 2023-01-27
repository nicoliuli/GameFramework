package com.game.core;

import com.game.core.constant.MsgHandlerConstant;
import com.game.core.dto.User;
import com.game.handler.AccountMsgHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * msgHandler的方法映射
 * 单实例
 */
public class MsgHandlerMapping {


    private static MsgHandlerMapping instance = new MsgHandlerMapping();
    private MsgHandlerMapping(){

    }

    public static MsgHandlerMapping instance(){
        return instance;
    }
    /**
     * port 到 msgHandler的映射
     */
    private Map<String, MsgParamWrapper> msgHandlerMap = new HashMap();

    public void regMsgHandler() {
        //======= 注册MsgHandler
        AccountMsgHandler accountMsgHandler = AccountMsgHandler.inst();
        msgHandlerMap.put(MsgHandlerConstant.ACCOUNTMSGHANDLER_LOGIN, new MsgParamWrapper(accountMsgHandler::login, Object.class));
        msgHandlerMap.put(MsgHandlerConstant.ACCOUNTMSGHANDLER_VERIFY, new MsgParamWrapper(accountMsgHandler::verify, User.class));
        msgHandlerMap.put(MsgHandlerConstant.ACCOUNTMSGHANDLER_EVENT, new MsgParamWrapper(accountMsgHandler::event, User.class));
    }

    public MsgParamWrapper getMsgParamWrapper(String msgHandlerId) {
        return msgHandlerMap.get(msgHandlerId);
    }

}
