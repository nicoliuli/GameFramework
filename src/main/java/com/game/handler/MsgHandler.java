package com.game.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

abstract public class MsgHandler {
    public static volatile Map<Class, MsgHandler> instances = new ConcurrentHashMap<>();
    static {
        init();
    }

    public static void init(){
        instances.put(AccountMsgHandler.class,new AccountMsgHandler());
    }

    protected static <T extends MsgHandler> T inst(Class<?> clazz){
        Object inst = instances.get(clazz);
        return (T)inst;
    }
}
