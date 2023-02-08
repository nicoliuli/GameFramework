package com.game.core;

import com.game.core.obj.GlobalHumanObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 全局用户信息
 */
public class GlobalHumanObjectInfo {
    private static Map<Integer, GlobalHumanObject> datas = new ConcurrentHashMap<>();


    public static void add(GlobalHumanObject globalHumanObject) {
        datas.put(globalHumanObject.getHumanId(), globalHumanObject);
    }

    public static void remove(Integer humanId) {
        datas.remove(humanId);
    }
}
