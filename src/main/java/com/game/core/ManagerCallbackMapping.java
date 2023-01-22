package com.game.core;

import com.game.core.constant.ManagerConstant;
import com.game.core.constant.MsgHandlerConstant;
import com.game.core.dto.User;
import com.game.core.func.Func2;
import com.game.core.util.Param;
import com.game.handler.AccountMsgHandler;
import com.game.manager.AccountManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 单实例
 */
public class ManagerCallbackMapping {

    private static ManagerCallbackMapping instance = new ManagerCallbackMapping();

    private ManagerCallbackMapping(){

    }

    public static ManagerCallbackMapping instance(){
        return instance;
    }

    private Map<String, Object> managerCallbackMap = new HashMap();

    public void reg() {
        //======== 注册manage callback
        AccountManager accountManager = AccountManager.inst();
        Func2<Object, Param> verify_result = accountManager::_verify_result;
        managerCallbackMap.put(ManagerConstant.ACCOUNTMANAGER_RESULT_VERIFY,verify_result);
    }

    public Object getCallbackFunc(String methodKey){
        return managerCallbackMap.get(methodKey);
    }


}
