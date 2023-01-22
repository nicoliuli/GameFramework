package com.game.manager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

abstract public class ManagerBase {
    public static volatile Map<Class, ManagerBase> instances = new ConcurrentHashMap<>();
    static {
        init();
    }

    public static void init(){
        instances.put(AccountManager.class,new AccountManager());
    }

    protected static <T extends ManagerBase> T inst(Class<?> clazz){
        Object inst = instances.get(clazz);
        return (T)inst;
    }
}
