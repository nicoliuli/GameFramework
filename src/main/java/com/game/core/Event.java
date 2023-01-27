package com.game.core;

import com.game.core.constant.EventKey;
import com.game.core.func.*;
import com.game.core.util.Param;
import com.game.manager.AccountManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件监听，reg的方法只能注册manager里的方法
 */
public class Event {
    private static Map<String, List<Func1<Param>>> map = new HashMap<>();
    static {
        AccountManager accountManager = AccountManager.inst();
        reg(EventKey.TEST_EVENT,accountManager::testEvent);
        reg(EventKey.TEST_EVENT1,accountManager::testEvent1);
        reg(EventKey.TEST_EVENT,accountManager::testEvent2);
        reg(EventKey.TEST_EVENT1,accountManager::testEvent2);
    }

    public static void fire(String eventKey, Param param) {
        List<Func1<Param>> funs = map.get(eventKey);
        if (funs == null) {
            return;
        }
        for (Func1 fun : funs) {
            fun.apply(param);
        }
    }

    private static void reg(String eventKey, Func1<Param> fun) {
        List<Func1<Param>> funs = map.get(eventKey);
        if (funs == null) {
            funs = new ArrayList<>();
        }
        funs.add(fun);
        map.put(eventKey, funs);
    }
}
