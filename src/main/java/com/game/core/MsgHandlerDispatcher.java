package com.game.core;


import com.alibaba.fastjson.JSON;
import com.game.core.func.Func1;

import java.util.concurrent.LinkedBlockingQueue;

public class MsgHandlerDispatcher extends Thread {
    private LinkedBlockingQueue<MsgCall> queue;

    private MsgHandlerMapping handlerMapping;

    public MsgHandlerDispatcher() {
        this.queue = new LinkedBlockingQueue<>();
        handlerMapping = new MsgHandlerMapping();
    }


    @Override
    public void run() {
        handlerMapping.reg();
        loop();
    }

    public void loop() {
        while (true) {
            while (!queue.isEmpty()) {
                // 找到对应的function
                MsgCall msgCall = queue.poll();
                callMsgHandler(msgCall);
            }
        }
    }

    public void callMsgHandler(MsgCall msgCall) {
        Call call = msgCall.getCall();
        HumanObject humanObj = msgCall.getHumanObj();
        String msgHandlerId = call.getMsgHandlerId();
        MsgParamWrapper msgParamWrapper = handlerMapping.getMsgParamWrapper(msgHandlerId);

        Func1<MsgParam> func1 = msgParamWrapper.getFunc1();
        Class clazz = msgParamWrapper.getClazz();

        MsgParam msgParam = new MsgParam();
        msgParam.setParam(JSON.parseObject(call.getJsonParam(), clazz));
        msgParam.setHumanObject(humanObj);
        func1.apply(msgParam);
    }

    public void addQueue(MsgCall call) {
        this.queue.add(call);
    }


}
