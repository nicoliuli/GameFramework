package com.game.core;

import com.game.core.func.Func1;

/**
 *  msgHandler的注册信息
 */
public class MsgParamWrapper {
    /**
     * 函数式接口
     */
   private Func1<MsgParam> func1;
    /**
     * 请求参数转化为class的类型
     */
   private Class clazz;

    public MsgParamWrapper() {
    }

    public MsgParamWrapper(Func1<MsgParam> func1, Class clazz) {
        this.func1 = func1;
        this.clazz = clazz;
    }

    public Func1<MsgParam> getFunc1() {
        return func1;
    }

    public Class getClazz() {
        return clazz;
    }

}
