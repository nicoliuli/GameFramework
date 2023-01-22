package com.game.core.util;

public class Param {

    private Object[] param;

    public Param(Object... objs){
        this.param = objs;
    }

    public Param build() {
        return this;
    }

    public Object[] getParam() {
        return param;
    }
}
