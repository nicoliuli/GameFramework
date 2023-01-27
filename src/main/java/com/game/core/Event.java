package com.game.core;

import com.game.core.func.*;
import com.game.core.util.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件监听，reg的方法只能注册manager里的方法
 */
public class Event {
    private static Map<String, List<Object>> map = new HashMap<>();

    public static void fire(String eventKey, Param param) {
        List<Object> funs = map.get(eventKey);
        if (funs == null) {
            return;
        }
        for (Object fun : funs) {
            apply(fun, param);
        }
    }

    private static void apply(Object fun, Param param) {
        Object[] params = param.getParam();
        int length = param == null ? 0 : params.length;
        if (length == 0) {
            Func0 f = (Func0) fun;
            f.apply();
        } else if (length == 1) {
            Func1 f = (Func1) fun;
            f.apply(params[0]);
        } else if (length == 2) {
            Func2 f = (Func2) fun;
            f.apply(params[0], params[1]);
        } else if (length == 3) {
            Func3 f = (Func3) fun;
            f.apply(params[0], params[1], params[2]);
        } else if (length == 4) {
            Func4 f = (Func4) fun;
            f.apply(params[0], params[1], params[2], params[3]);
        }
    }

    public static void reg(String eventKey, Object fun) {
        List<Object> funs = map.get(eventKey);
        if (funs == null) {
            funs = new ArrayList<>();
        }
        funs.add(fun);
        map.put(eventKey, funs);

    }
}
